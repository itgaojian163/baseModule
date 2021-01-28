package com.tengshi.basemodule.retrofitNet;

import com.tengshi.basemodule.globalconfig.PathConfig;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : 项目基础IP配置,
 */
public class BaseUrlApi {


    /*==============================项目名称=================================*/
    public static final String PRO_USERCENTER = "usercenter/";
    public static final String PRO_VENUEBOOKING = "venuebooking/";
    public static final String PRO_VOLUNTEER = "volunteer/";
    public static final String PRO_ACTIVITY = "culturalactivity/";


    /*=============================End==============================*/

    /*====================基础IP配置=======================*/
    public static final String HOST = "http://124.67.110.246";
    public static final String PORT = ":8081/";

    public static final String BASE_SYSTEM_IP = HOST + PORT;

    public static final String USERCENTER_IP = BASE_SYSTEM_IP + PRO_USERCENTER;//用户中心

    //    public static final String IP = "http://192.168.0.103:7001/usercenter/";/* 测试本地 */
    //        public static final String BASE_SYSTEM_IP = "http://121.36.71.250:8084/";/* 集宁企业IP */
//    public static final String BASE_SYSTEM_IP = HOST + PORT;/* 集宁企业正式IP */

    public static final String SOCKET_IP = BASE_SYSTEM_IP + "social/appws";/*SocketIP*/
    //    public static final String BASE_SYSTEM_IP = "http://192.168.0.7:8888/";/* 测试 */
//    public static final String BASE_SYSTEM_IP = "http://192.168.0.103:7006/";/* 测试本地IP */
//    public static final String IP = "http://192.168.0.103:7001/usercenter/";/* 测试本地IP */
    /*=============================End==============================*/


    /*=========================项目IP配置============================*/
    public static final String BASE_ACTIVIY_IP = BASE_SYSTEM_IP + PRO_ACTIVITY;/* 活动URL */
    public static final String BASE_VOLUNTEER_IP = BASE_SYSTEM_IP + PRO_VOLUNTEER;/* 志愿者 */
    public static final String BASE_LIVE_IP = BASE_SYSTEM_IP + "live/";
    public static final String BASE_LIVE_URL = BASE_SYSTEM_IP;//直播回放视频url
    public static final String BASE_PLACE_IP = BASE_SYSTEM_IP + PRO_VENUEBOOKING;/* 场馆 */
    public static final String BASE_CULTURAL_IP = BASE_SYSTEM_IP + "culturalactivity/";/* 文化URL */
    public static final String BASE_MINE = "http://192.168.0.108:8080/culturalactivity/";/* 我的URL */
    public static final String BASE_CULTURAL_SHARE = BASE_SYSTEM_IP + "social/";/* 文化分享 */
    public static final String BASE_SERVICE_CITY = BASE_SYSTEM_IP + "servicecity/";/* 网格化管理 */
    public static final String BASE_NEWS_IP = BASE_SYSTEM_IP + "news/";//新闻
    public static final String BASE_LEGACY_IP = BASE_SYSTEM_IP + "library/";//非遗数据库
    public static final String BASE_INSPECTION_IP = BASE_SYSTEM_IP + "inspection/";/*  隐患上报*/
    //    public static final String BASE_INSPECTION_IP = "http://192.168.0.103:7006/inspection/";/*隐患上报*/
    public static final String BASE_WORK_APPROVE = BASE_SYSTEM_IP + "approvingsystem/";/*公文审批*/
    /*===========================End================================*/


    /*=========================项目下载、图片配置===========================*/
    public static final String PROJECT_NAME = "app/";
    public static final String APP_DOWNLOAD_URL = HOST + PORT + "app/appversion/downloadapp/" + PathConfig.APP_VERSION_ID;
    public static final String APP_DOWNLOAD_URL_URL = "/usercenter/app/appversion/downloadapp/04f35ca3-a269-445a-b39f-9146c9b6bfde";
    public static final String APP_DOWNLOAD_BASE_UTL = "http://124.67.110.246:8081";
    public static final String BASE_SERVICECITY_IMG_URL = BASE_SERVICE_CITY + "route/file/downloadfile/true/";//网格图片文件路径
    public static final String BASE_ACTIVITY_IMG_URL = BASE_ACTIVIY_IP + "route/file/downloadfile/true/";
    public static final String BASE_VOLUNTEER_IMG_URL = BASE_VOLUNTEER_IP + "route/file/downloadfile/true/";
    public static final String BASE_NEWS_IMG_URL = BASE_NEWS_IP + "route/file/downloadfile/true/";
    public static final String BASE_LIVE_IMG_IP = BASE_LIVE_IP + "route/file/downloadfile/true/";
    public static final String BASE_IMG_URL = BASE_SYSTEM_IP + "route/file/downloadfile/true/";
    public static final String BASE_LEGACY_IMG_URL = BASE_LEGACY_IP + "route/file/downloadfile/true/";
    public static final String BASE_SOCIAL_IMG_URL = BASE_CULTURAL_SHARE + "route/file/downloadfile/true/";
    public static final String BASE_ENTERPRISE_IMG_URL = BASE_INSPECTION_IP + "route/file/downloadfile/true/";
    public static final String BASE_PLACE_IMG_IP = BASE_PLACE_IP + "route/file/downloadfile/true/";
    public static final String BASE_SHOW_PDF_URL = BASE_SERVICE_CITY + "app/reportcase/v2/showformalpdf/";
    /*=========================End==================================*/


    public static final int PHOTO_REQUEST = 233;
    public static final int VIDEO_REQUEST_ALBUM = 77;
    public static final int VIDEO_REQUEST_CAMERA = 88;
    public static final int CAMERA_REQUEST = 123;
    public static final int PHOTO_REQUEST_DIALOG = 333;
    public static final int CAMERA_REQUEST_DIALOG = 433;
    public static final int REQUEST_CULTURAL_SHARE = 7392;//文化分享发布请求码
    public static final int RESULT_CODE_SCOAL = 321;
    //地图页面展示类型
    public static final String TYPE_SHOW_MARKER = "442";//显示marker
    public static final String TYPE_NAVIGATION = "456";//显示导航
    public static final String TYPE_DEFAULT = "333";//默认


    //    public static final String BASE_NEWS_DETAIL = "http://192.168.0.104/";//新闻
    public static final String BASE_NEWS_DETAIL = BASE_SYSTEM_IP;//新闻
    //    public static final String BASE_IMG_URL = BASE_IP + "route/file/downloadfile/true/";

    public static final String APK_DOWNLOAD_URL = "downloadUrl";
    public static final String UPDATE_URL = "version.json";

}
