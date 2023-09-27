# 智慧城市模块分析

尝试使用compose重构，则部分代码无法复用框架以及考虑使用替代

- 引导页
  - viewpager左右滑动 with指示符
  - preference读写 ip port, init status -> Preferences DataStore
- 主页
  - 轮播图 auto viewpager
  - 搜索
  - RecyclerView -> LazyRows 
  - bottom tab view pager
- 个人中心
  - 图片上传
  - [修改已保存ip port]



## 基础要求

**应用名 | 主页toolbar title：智慧城市**

打包为release包发布



## 关键路径

- 任务周期： 2023.9.24 -9.28
- 交付物：智慧城市应用apk及源码
- 模块划分
  - 架构
    - ![Android 应用的应用图表模型](https://developer.android.com/static/topic/libraries/architecture/images/final-architecture.png?hl=zh-cn)
    - UI层
    - ViewModel层
    - Repository层
      - [数据持久库Room]
      - 网络请求Retrofit
      - 架构组件LiveData
      - 协程构建和启动, Kotlin Flow
      - 数据绑定适配器
    - 本地数据存储
      - Preferences DataStore
  - 引导页
    - 滑动引导页
    - 本地存储
  - 主页面
    - 网络请求
      - get -> json -> plan object
      - post : with form
    - RecyclerView -> LazyColumn
    - BottomAppBar
    - AppBar
    - EditText -> TextFieId
  - 个人中心
  - 全部服务
    - custom list: with checked status
  - 自选模块
- 任务安排：
  1. DAY1
     - [x] 规划剩下四天的任务安排
     - [x] 初步了解jetpack compose
     - [x] 学习简易kotlin语法
       - [x] 了解网络库retrofit
     - [x] 了解待学习内容
  2. DAY2
     - [x]  完成引导页界面
        - [x] 滑动引导页(Pager)
     - [x] [主页使用技术研究
        - [x]  SearchBar
        - [x]  BottomNavigation
        - [x]  Scaffold
  3. DAY3
     - [x] 学习
       - [x] 了解kt flow 协程
       - [x] Preferences DataStore实现本地存储功能
       - [x] viewModel & liveData
     - [x] 实现
       - [x] 主页
         - [x] banner -> HorizontalPager
         - [x] tablayout -> TabRow
         - [x] 协程加载数据 -> 考虑引入Net网络请求框架
       - [x] 个人中心
     - [ ] 可选
       - [ ] 学习依赖注入
       - [ ] 重构抽象考虑搭建基本结构
  4. DAY4
     - [ ] 实现全部服务
     - [ ] 挑选自选模块尝试重构代码实现
     - [ ] 打包应用测试
  5. DAY5
     - [ ] 完善应用
     - [ ] 提交交付物

## 学习笔记



###  DAY1

- [Kotlin Bootcamp for Programmers | 这个我没做，看文档简单了解了一下语法](https://www.udacity.com/course/kotlin-bootcamp-for-programmers--ud9011) 

- [Retrofit (square.github.io)](https://square.github.io/retrofit/)

- [应用架构指南  | Android 开发者  | Android Developers](https://developer.android.com/topic/architecture?hl=zh-cn)

- [gold-miner/TODO/getting-started-with-retrofit](https://github.com/xitu/gold-miner/blob/master/TODO/getting-started-with-retrofit.md)

- [使用 Kotlin DSL 进行依赖管理 - 简书 (jianshu.com)](https://www.jianshu.com/p/461d4a249b71)

- [json2kt.com](https://json2kt.com/)

- [JSON To Kotlin Class (JsonToKotlinClass) -json2kt clz插件](https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-/versions#tabs)

  



#### 使用Kotlin网络通信

- Retrofit—— 网络请求库

- Gson —— 将 json 与 对象 互转的库

- Retrofit converter-Gson —— Retrofit插件，提供GsonConverterFactory帮助Gson与Retrofit交互

- 依赖部分变动为kotlin dsl —— 简化使用只了解为更改为

- ```kotlin
  dependencies {
      implementation ("library url")
  }
  ```

  此处三个组件库的实现我直接借助chatgpt生成相应示例代码，我补充细节的形式来直接先敲一次例子了解有哪些基本功能

  

  **相关kt语法**：

  - data class

    - 类名后直接声明主构造函数，在其中定义一些类属性，使它们称为类成员属性
    - 会将主构造函数中的成员自动生成 getter, setter 以及 生成equals, hashCode, toString

  - `@SerializedName` 注解

    - 指定json字段与kt属性名间的映射

  - suspend —— kotlin协程概念之一

    - 定义挂起函数，表示函数可被挂起和恢复执行
    - 使其能与协程一同工作

  - 双冒号`::`引用对象语法

    - 例如`ApiService::class` 表示对 `ApiService` 类的引用，而 `.java` 则表示获取该类的 Java Class 对象。

      > 在 Retrofit 中，我们需要传递一个 Java Class 对象给 `retrofit.create()` 方法来创建相应的 API 接口实例。因此，我们使用 `ApiService::class.java` 来获取 `ApiService` 的 Java Class 对象，然后在 `retrofit.create()` 方法中使用它。

  - object对象表达式语法

    - 可用于创建匿名对象，还可将泛型作为类型参数

      ```kotlin
      val callback = object : Callback<List<String>> {
          // 实现 Callback 接口的方法
          override fun onResponse() {
          }
      }
      ```

      

  

  **retrofit相关语法**：

  - 通过接口定义 api接口
  - 通过构造实例使用方法
  - 通过注解定义请求类型，请求头，参数
    - @Query(String)注解 拼接参数到URL中
    - @Header(String) 添加请求头
    - @Body 将参数添加到body中

  

  **gson相关语法**：

  - setLenient() —— 设置Gson解析器为宽松模式
    - 使解析器尝试解析不符合json规范的内容，避免遇到错误立刻抛出异常



实现了完整的一个retrofit get post的流程，封装方法相关的语法知识太多，明天补上

```kotlin

```



### DAY2



#### 学习Compose基本布局

- [Compose 布局基础知识  | 作了解用](https://developer.android.com/jetpack/compose/layouts/basics?hl=zh-cn) 

- [Compose 中的基本布局 (android.com)](https://developer.android.com/codelabs/jetpack-compose-layouts?hl=zh-cn#0)
- [Compose 与 Kotlin 的兼容性对应关系  | 注意compose版本需要相应的兼容kotlin版本](https://developer.android.com/jetpack/androidx/releases/compose-kotlin?hl=zh-cn)
- [Compose 中的分页器  | Jetpack Compose  | Android Developers](https://developer.android.com/jetpack/compose/layouts/pager?hl=zh-cn)

尝试实现引导页时发现对新布局如何定位不够了解，决定开一个codelab

花了两个单位时跟着敲完一个codelab

收获很多，大部分页面组件都包括了相应实例

##### 修改本地项目kt版本保证兼容

> but don't say I didn't warn you

```bash
This version (1.4.8) of the Compose Compiler requires Kotlin version 1.8.22 but you appear to be using Kotlin version 1.9.10 which is not known to be compatible.  Please consult the Compose-Kotlin compatibility map located at https://developer.android.com/jetpack/androidx/releases/compose-kotlin to choose a compatible version pair (or `suppressKotlinVersionCompatibilityCheck` but don't say I didn't warn you!).
```

打开codelab项目出现以上警告，选择打开项目根目录下的`build.gradle`文件并修改plugins快kotlin插件版本

```groovy
plugins {
    ...
    id 'org.jetbrains.kotlin.android' version '1.8.22' apply false
    ...
}
```







#### 通过Pager实现引导页

```kotlin
...

class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ...
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroView() {

    val context = LocalContext.current

    val imageList = listOf(
        ...
    )

    val pagerState = rememberPagerState { imageList.size }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { pageIndex ->
        val imageId = imageList[pageIndex]

        Image(
            painter = painterResource(id = imageId),
            contentDescription = "intro",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )



    }

    // 指示器
    Box(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        ) {
            imageList.forEachIndexed { index, _ ->
                val color = if (index == pagerState.currentPage) Color.White else Color.Gray
                Box(
                    Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(color)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

    if (pagerState.currentPage == imageList.lastIndex) {

        Box(
            Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "网络设置",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 25.dp, end = 25.dp)
                    .clickable {

                    },
            )
            Button(
                onClick = {
//                        check if net config setup yet

                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp, start = 50.dp, end = 50.dp)
            ) {
                Text(
                    text = "进入主页"
                )

            }


        }
    }
}
```

#### 搭建主页

搭UI，配合chatgpt用了一堆像`状态保存`、`数据对`、`槽位匹配`之类的方法，只求先搭好主页和个人中心

`BottomNavigation`和`TopAppBar`联动(修改appBarText)以及设置相应`Fragment`都有了大量的改动，查文档查了一晚上

> 然后发现不用NavHost直接在Content处匹配appBarText不就完了，虽然可能会有隐患(与BottomNavigation联调方面)

分好了Fragment和弄好SearchBar

### DAY3

- [zhujiang521/Banner (参考学习 | 直接引用库)](https://github.com/zhujiang521/Banner/blob/master/banner/src/main/java/com/zj/banner/BannerPager.kt)
- [coil-kt/coil: 加载图片库](https://coil-kt.github.io/coil/compose/)
- [Duration  | Android Developers](https://developer.android.com/reference/kotlin/java/time/Duration.html)
- [Exploring the Official Pager Composable in Jetpack Compose | Pager实现Banner和Tabs](https://medium.com/@domen.lanisnik/exploring-the-official-pager-in-compose-8c2698c49a98)
- [androidx.compose.material  | Android Developers](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#LocalContentAlpha())
- [gradle - Android Studio maven { url "https://jitpack.io" } can't download - Stack Overflow](https://stackoverflow.com/questions/50389211/android-studio-maven-url-https-jitpack-io-cant-download)
- [Net (liangjingkanji.github.io)](https://liangjingkanji.github.io/Net/index.html)
- [Kotlin/kotlinx.serialization: 序列化注解](https://github.com/Kotlin/kotlinx.serialization)
- [Android 之 Compose 开发基础 - 从互联网获取数据  | Android Developers](https://developer.android.com/courses/pathways/android-basics-compose-unit-5-pathway-1?hl=zh-cn#codelab-https://developer.android.com/codelabs/basic-android-kotlin-compose-coroutines-kotlin-playground)



#### 继续搭页面



#### 折腾依赖库

要下载一个库应为仓库配置半天没弄下来最后直接剪裁代码了，结果搞另一个库依赖的时候解决了

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url = uri("https://www.jitpack.io" ) }
    }
}

```

引入`import androidx.compose.ui.util.lerp`时才发现少了个util库

```kotlin
dependencies {
    implementation("androidx.compose.ui:ui-util:1.5.1")
}
```



##### Pager搭Banner

花了一个上午看一坨文档，连加个依赖都卡住，直接从一个Banner库里扒代码才搞定

### DAY4

- [android/sunflower: A gardening app illustrating Android development best practices with migrating a View-based app to Jetpack Compose. (github.com)](https://github.com/android/sunflower)





#### 根据官方示例重构代码

```markdown
Note: As Compose cannot render HTML code in Text yet. The AndroidViewBinding API is used to embed a TextView in Compose. See the PlantDescription composable in the PlantDetailView file.
```

- 注意compose Text暂不支持html code, 官方示例也提到了用binding 获取TextView去组合使用

#### Splash

- [Jetpack Compose: Splash Screen API | by Mesut G. | Medium](https://developersancho.medium.com/jetpack-compose-splash-screen-api-36ca40c6196b)





## 参考引用

- [What's the equivalent of AppBar in Jetpack Compose? 用于确认更换的组件](https://www.jetpackcompose.app/What-is-the-equivalent-of-AppBar-in-Jetpack-Compose) 
- [Android Jetpack 开发资源 - 遇事不决查文档](https://developer.android.com/jetpack?hl=zh-cn) 
- [Retrofit (square.github.io)](https://square.github.io/retrofit/)
- [google/gson](https://github.com/google/gson)
- [使用 Kotlin DSL 进行依赖管理 - 引入依赖发现又有变动](https://www.jianshu.com/p/461d4a249b71) 
- [json2kt](https://json2kt.com/)
- [JSON To Kotlin Class (JsonToKotlinClass) -json2kt clz插件](https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-/versions#tabs)
- [Compose 布局基础知识  | 作了解用](https://developer.android.com/jetpack/compose/layouts/basics?hl=zh-cn) 

- [Compose 中的基本布局 (android.com)](https://developer.android.com/codelabs/jetpack-compose-layouts?hl=zh-cn#0)
- [Compose 与 Kotlin 的兼容性对应关系  | 注意compose版本需要相应的兼容kotlin版本](https://developer.android.com/jetpack/androidx/releases/compose-kotlin?hl=zh-cn)
- [Compose 中的分页器  | Jetpack Compose  | Android Developers](https://developer.android.com/jetpack/compose/layouts/pager?hl=zh-cn)

- [ViewModel 概览  | Android 开发者  | Android Developers](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=zh_cn)

- [协程 · Kotlin 官方文档 中文版 (kotlincn.net)](https://book.kotlincn.net/text/coroutines-overview.html)

- [zhujiang521/Banner (参考学习 | 直接引用库)](https://github.com/zhujiang521/Banner/blob/master/banner/src/main/java/com/zj/banner/BannerPager.kt)
- [coil-kt/coil: 加载图片库](https://coil-kt.github.io/coil/compose/)
- [Duration  | Android Developers](https://developer.android.com/reference/kotlin/java/time/Duration.html)
- [Exploring the Official Pager Composable in Jetpack Compose | Pager实现Banner和Tabs](https://medium.com/@domen.lanisnik/exploring-the-official-pager-in-compose-8c2698c49a98)
- [androidx.compose.material  | Android Developers](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#LocalContentAlpha())
- [gradle - Android Studio maven { url "https://jitpack.io" } can't download - Stack Overflow](https://stackoverflow.com/questions/50389211/android-studio-maven-url-https-jitpack-io-cant-download)
- [Net (liangjingkanji.github.io)](https://liangjingkanji.github.io/Net/index.html)
- [Kotlin/kotlinx.serialization: 序列化注解](https://github.com/Kotlin/kotlinx.serialization)
- [Android 之 Compose 开发基础 - 从互联网获取数据  | Android Developers](https://developer.android.com/courses/pathways/android-basics-compose-unit-5-pathway-1?hl=zh-cn#codelab-https://developer.android.com/codelabs/basic-android-kotlin-compose-coroutines-kotlin-playground)



#### 继续搭页面

- chatgpt | copilot

  

