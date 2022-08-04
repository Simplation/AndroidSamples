package com.simplation.cameraalbum

import android.app.Activity
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.simplation.cameraalbum.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var imageUri: Uri
    private lateinit var binding: ActivityMainBinding
    private lateinit var takePhoto: ActivityResultLauncher<Intent>
    private lateinit var choosePhoto: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerResult()

        // 调用摄像头拍照
        binding.takePhoto.setOnClickListener {
            val outputImage = File(externalCacheDir, "output_image.jpg")

            try {
                if (outputImage.exists()) {
                    outputImage.delete()
                }

                outputImage.createNewFile()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            imageUri = if (Build.VERSION.SDK_INT >= 24) {
                FileProvider.getUriForFile(
                    this,
                    "com.simplation.cameraalbum.fileprovider",
                    outputImage
                )
            } else {
                Uri.fromFile(outputImage)
            }

            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            takePhoto.launch(intent)
            // startActivityForResult(intent, TAKE_PHOTO)
        }

        // 从相册获取图片
        binding.chooseFromAlbum.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            } else {
                openAlbum()
            }
        }
    }

    private fun registerResult() {
        takePhoto =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    try {
                        val bitmap =
                            BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                        binding.picture.setImageBitmap(bitmap)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        choosePhoto =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(it.data)
                    } else {
                        handleImageBeforeKitKat(it.data)
                    }
                }
            }
    }

    private fun openAlbum() {
        Intent("android.intent.action.GET_CONTENT").apply {
            type = "image/*"
            choosePhoto.launch(this)
            //startActivityForResult(this, CHOOSE_PHOTO)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum()
            } else {
                Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*
    @Deprecated("outdated method")
    @SuppressLint("ObsoleteSdkInt")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            try {
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                binding.picture.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (requestCode == CHOOSE_PHOTO && resultCode == Activity.RESULT_OK) {
            if (Build.VERSION.SDK_INT >= 19) {
                handleImageOnKitKat(data)
            } else {
                handleImageBeforeKitKat(data)
            }
        }
    }*/

    private fun handleImageBeforeKitKat(data: Intent?) {
        val uri = data!!.data
        val imagePath = getImagePath(uri, null)
        displayImage(imagePath)
    }

    private fun handleImageOnKitKat(data: Intent?) {
        var imagePath: String? = null
        val uri = data!!.data

        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果 Document 类型是 uri 则通过 document id 处理
            val docId = DocumentsContract.getDocumentId(uri)

            if ("com.android.providers.media.documents" == uri!!.authority) {
                val id = docId.split(":")[1]
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android.providers.downloads.documents" == uri.authority) {
                val contentUri = ContentUris.withAppendedId(
                    Uri
                        .parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(docId)
                )
                imagePath = getImagePath(contentUri, null)
            }
        } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
            imagePath = getImagePath(uri, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            imagePath = uri.path
        }

        // 展示图片
        displayImage(imagePath)
    }

    private fun displayImage(imagePath: String?) {
        if (imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            binding.picture.setImageBitmap(bitmap)
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImagePath(uri: Uri?, selection: String?): String? {
        var path: String? = null

        val cursor = contentResolver.query(uri!!, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }
}