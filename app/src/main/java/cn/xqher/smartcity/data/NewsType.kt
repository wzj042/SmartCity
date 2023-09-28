package cn.xqher.smartcity.data

import com.google.gson.annotations.SerializedName
import com.zj.banner.model.BaseBannerBean

data class NewsType(
    @SerializedName("data")
    val dataList: List<NewsTypeData>
)

data class NewsTypeData(
    override var data: Any?,
    val id: Int = 0,
    val name: String = "",
    val sort: Int = 0,
    val status: String = "",
): BaseBannerBean()
