package cn.xqher.smartcity.data

import com.zj.banner.model.BaseBannerBean

enum class CoverType {
    Right, Left, Without
}
abstract class BaseListData : BaseBannerBean() {
    //    ImageUrl or ResId with parent inner data
    //    位于列表四方的四个文本
    abstract val title: String
    abstract val content: String
    abstract val status: String
    abstract val extra: String
    abstract val coverType: CoverType
}

data class ListData (
    override val title: String = "",
    override val content: String = "",
    override val status: String = "",
    override val extra: String = "",
    override val coverType: CoverType = CoverType.Right,
    override val data: Any? = null
) : BaseListData()