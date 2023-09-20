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