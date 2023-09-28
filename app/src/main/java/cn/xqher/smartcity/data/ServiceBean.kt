package cn.xqher.smartcity.data

import com.zj.banner.model.BaseBannerBean

class ServiceBean(
    val rows: List<ServiceRow>,
)

data class ServiceRow(
    override var data: Any?,
    val id: Int = 0,
    val imgUrl: String = "",
    val isRecommend: String = "",
    val serviceDesc: String = "",
    val serviceName: String = "",
    val serviceType: String = "",
    val sort: Int = 0,
): BaseBannerBean()
