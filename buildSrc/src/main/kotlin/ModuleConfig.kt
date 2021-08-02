import kotlin.reflect.full.memberProperties

/**
 * @作者: Simplation
 * @日期: 2021/8/2 11:41
 * @描述:
 * @更新:
 */
object ModuleConfig {
    // 通过反射访问所有的常量
    const val App = ":app"
    private const val CommonLib = ":commonlib"

    private fun getAllModules() = ModuleConfig::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

    fun getDynamicFeatureModules() = getAllModules()
        .filter { it.startsWith(CommonLib) }
        .toSet()
}