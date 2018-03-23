package network.exception

import com.google.gson.JsonParseException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by AxeChen on 2018/3/19.
 * 自定义异常，比如网络异常，解析异常等
 */
class CustomException {

    companion object {
        /**
         * 未知错误
         */
        var UNKNOWN: Int = 1000

        /**
         * 解析错误
         */
        var PARSE_ERROR: Int = 1001

        /**
         * 网络错误
         */
        var NETWORK_ERROR: Int = 1002

        /**
         * HTTP错误
         */
        var HTTP_ERROR: Int = 1003

        /**
         * 将本地异常解析成ApiException
         */
        fun handleException(cause: Throwable?): ApiException? {
            var exception: ApiException? = null
            if (cause is JsonParseException) {
                exception = ApiException(cause?.message, cause, PARSE_ERROR)
            } else if (cause is UnknownHostException ||
                    cause is SocketTimeoutException ||
                    cause is ConnectException) {
                exception = ApiException(cause?.message, cause, NETWORK_ERROR)
            } else {
                exception = ApiException(cause?.message, cause, UNKNOWN)
            }
            return exception
        }

    }


}