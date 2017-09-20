
# Eyepetizer
    主要是为了通过仿写APP更好的学习kotlin(选择该APP原因主要是因为有大佬已经写过了，站在巨人的肩膀站得高看的远)

# 计划
    主页
    发现页面（包含热门，分类，作者）
    关注页面
    播放页面   
 
# 目前进度 

## 启动页面
    开启启动画面渐变 ( Handler+Thread )

## 首页
    显示每日精选自动轮播自动播放5秒小视频介绍 (viewpager + indicator)
    显示推荐视频选项（简单的添加显示在recyclerView中 ）
    实现每日精选文字逐字显示（换了个感觉更容易理解的方式，但是显示数字时会有位置差距，待解决）
    实现下拉放大图片刷新（照搬大佬的自定义轮子）
    
## 播放页面

    旋转和点击控制全屏播放
    实现显示作品相关信息(暂未能实现查看评论功能)
    实现相关视频推荐
## 发现页面
    实现显示分类小页面
## bug
    打开首页会占用音频播放导致音乐播放器停止工作(考虑空的播放器能否禁用声音，假装这个锅不是我的)
    播放视频时莫名的会停止（有时候没问题，在研究研究，毫无头绪）
    发现页面第二次点进无信息显示，需要多次滑动才有信息（据说是viewpagerAdapter的锅，还未解决）
## TODO
    修复已发现的bug
    完善发现页面的2个小分页
# 实现方式
    TabLayout+Fragment 实现底部导航栏(这个感觉还是比较简单的，网上抄了就懂了)
    okhttp+retrofit+rxjava 实现网络请求框架（一脸懵逼，表示目前还处于拿来能用的阶段,虽然百度上都说这套连招比较好，然而不知道为什么）
    mvp 框架（不明觉厉的框架）
    
# 关于我
    联系：497533265@qq.com    
## 声明
    Api 数据都是来自开眼视频，数据接口均属于非正常渠道获取，请勿用于商业用途，原作公司拥有所有权利。
    
## 参考
    https://github.com/kaikaixue/Eyepetizer
    https://github.com/LRH1993/Eyepetizer-in-Kotlin
    感谢大佬们的资源，向大佬们学习。
