package com.zht.weather

import java.io.Serializable

data class WeatherData(val city:String, val tmp:String, val desc:String,val cont_code:String, val fengli:String,
                       val fengxiang:String/*, val forecast:ArrayList<WeatherOneday>*/) :Serializable{
    data class WeatherOneday(val date:String, val high:String, val low:String, val fengli:String,
                             val fengxiang:String, val type:String):Serializable
}

/*{
    "code": 200,
    "msg": "成功!",
    "data": {
    "yesterday": {
    "date": "7日星期二",
    "high": "高温 15℃",
    "fx": "无持续风向",
    "low": "低温 12℃",
    "fl": "\u003c![CDATA[\u003c3级]]\u003e",
    "type": "小雨"
},
    "city": "成都",
    "aqi": null,
    "forecast": [{
    "date": "8日星期三",
    "high": "高温 19℃",
    "fengli": "\u003c![CDATA[\u003c3级]]\u003e",
    "low": "低温 14℃",
    "fengxiang": "无持续风向",
    "type": "小雨"
}],
    "ganmao": "昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。",
    "wendu": "18"
}
}*/
