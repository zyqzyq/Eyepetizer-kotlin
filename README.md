
# Eyepetizer
    主要是为了通过仿写APP更好的学习kotlin(选择该APP原因主要是因为有大佬已经写过了，站在巨人的肩膀站得高看的远)

# 计划
    主页
    发现页面（包含热门，分类，作者）
    关注页面
    播放页面   
    全部作者页面
    全部分类页面
    分类详情页面（包含首页，全部，作者，专辑）
    排行榜页面（包含周排行，月排行，总排行）
    搜索页面
    
# 目前进度 

## 启动页面
    开启启动画面渐变 ( Handler+Thread )

## 首页
    显示每日精选自动轮播自动播放5秒小视频介绍 (viewpager + indicator)(增加无限循环，优化最后一页跳转卡顿)
    显示推荐视频选项（简单的添加显示在recyclerView中 ）（每日精选的视频右下角添加图片标识）
    实现每日精选文字逐字显示
    实现下拉放大图片刷新
    增加再按一次退出提示
## 播放页面
    旋转和点击控制全屏播放
    实现显示作品相关信息(暂未实现缓存功能)
    实现相关视频推荐
## 发现页面
    实现热门小页面
    实现banner轮播图（用的git大佬的轮子,链接在最底下）
    实现热门视频推荐
    实现热门排行链接（横向的recyclerView实现）
    实现分类小页面
    页面的item主要用的banner轮子(有一些细微的改动)
    实现作者小页面
    使用横向的recyclerView实现最新作者推荐栏的滑动
## 关注页面
    主要调用之前的fragment 快速实现
## 我的页面
    主要就显示显示（准备实现缓存功能）
## 全部作者页面
    与发现作者小页面一样
## 全部分类页面
    使用gridView显示分类列表（不知道热门排行，热门专题，360全景的api就没添加）
## 分类详情页面
    scrollView + tabLayout + viewPager + Fragment  实现4个小分页的显示
    
## 排行榜页面
    使用和发现页面类似的方法，由于子页面数据类型一样，用同一个fragment实现。
    
## 搜索页面
    偷懒使用了activity + recyclerView简单实现
## bug
    状态栏无法完全透明
## TODO
    准备实现缓存功能
    准备优化界面显示
   
# 实现方式
    mvp 框架
    okhttp+retrofit+rxjava 实现网络请求框架
    TabLayout+Fragment 实现底部导航栏
    TabLayout + ViewPager + Fragment 实现分页显示
    
# 关于我
    联系：497533265@qq.com    
## 声明
    Api 数据都是来自开眼视频，数据接口均属于非正常渠道获取，请勿用于商业用途，原作公司拥有所有权利。
    
## 参考
    https://github.com/kaikaixue/Eyepetizer
    https://github.com/LRH1993/Eyepetizer-in-Kotlin
    https://github.com/youth5201314/banner
    https://github.com/CarGuo/GSYVideoPlayer
    感谢大佬们的资源，向大佬们学习。
