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
    override val extra: String = "",
    override val coverType: CoverType = CoverType.Right,
    override val status: String = "",
    override val title: String = "",
    override val content: String = "",
    val appType: String = "",
    val commentNum: Int = 0,
    val cover: String = "",
    val createBy: String = "",
    val createTime: String = "",
    val hot: String = "",
    val id: Int = 0,
    val likeNum: Int = 0,
    val publishDate: String = "",
    val readNum: Int = 0,
    val top: String = "",
    val type: String = "",
    val updateBy: String = "",
    val updateTime: String = "",
) : BaseListData()
