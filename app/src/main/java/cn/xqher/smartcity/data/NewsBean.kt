package cn.xqher.smartcity.data

import com.zj.banner.model.BaseBannerBean

data class NewsBean(
    val code: Int,
    val msg: String,
    val rows: List<NewsRow>,
    val total: Int
)

data class NewsRow(
    override var data: Any?,
    val appType: String = "",
    val commentNum: Int = 0,
    val content: String = "",
    val cover: String = "",
    val createBy: String = "",
    val createTime: String = "",
    val hot: String = "",
    val id: Int = 0,
    val likeNum: Int = 0,
    val publishDate: String = "",
    val readNum: Int = 0,
    val status: String = "",
    val title: String = "",
    val top: String = "",
    val type: String = "",
    val updateBy: String = "",
    val updateTime: String = ""
):BaseBannerBean()
