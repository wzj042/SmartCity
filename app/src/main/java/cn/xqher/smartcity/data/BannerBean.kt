package cn.xqher.smartcity.data

import com.zj.banner.model.BaseBannerBean

data class BannerBean(
    val rows: List<BannerRow> = listOf()
)
data class BannerRow(
    override var data: Any? = null,
    val advImg: String = "",
    val advTitle: String = "",
    val appType: String = "",
    val createBy: String = "",
    val createTime: String = "",
    val id: Int = 0,
    val servModule: String = "",
    val sort: Int = 1,
    val status: String = "",
    val targetId: Int = 1,
    val type: String = "",
    val updateBy: String = "",
    val updateTime: String = ""
): BaseBannerBean()