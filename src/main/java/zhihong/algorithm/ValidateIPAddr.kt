package zhihong.algorithm

/**
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 *
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 *
 * 示例 1：
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 *
 * 示例 2：
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 *
 * 示例 3：
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-ip-address
 */
object ValidateIPAddr {

    @JvmStatic
    fun main(args: Array<String>) {
        println(validIPAddress("172.16.254.1"))
        println(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"))
        println(validIPAddress("256.256.256.256"))
    }

    private const val IPV4 = "IPv4"
    private const val IPV6 = "IPv6"
    private const val NEITHER = "Neither"
    private const val SEPARATOR_IPV4 = "."
    private const val SEPARATOR_IPV6 = ":"

    /**
     * 执行用时：160 ms, 在所有 Kotlin 提交中击败了58.33%的用户
     * 内存消耗：34.8 MB, 在所有 Kotlin 提交中击败了75.00%的用户
     */
    private fun validIPAddress(queryIP: String): String {
        if (isIpv4(queryIP)) {
            return IPV4
        }
        if (isIpv6(queryIP)) {
            return IPV6
        }
        return NEITHER
    }

    private fun isIpv4(queryIP: String): Boolean {
        val ipParts = queryIP.split(SEPARATOR_IPV4)
        if (ipParts.size != 4) {
            return false
        }
        ipParts.forEach { ipPart ->
            if (ipPart.startsWith("0") && ipPart != "0") {
                return false
            }
        }
        ipParts.forEach { ipPart ->
            try {
                val num = ipPart.toInt()
                if (num < 0 || num > 255) {
                    return false
                }
            } catch (e: NumberFormatException) {
                return false
            }
        }
        return true
    }

    private fun isIpv6(queryIP: String): Boolean {
        val ipParts = queryIP.split(SEPARATOR_IPV6)
        if (ipParts.size != 8) {
            return false
        }
        ipParts.forEach { ipPart ->
            if (ipPart.length > 4 || ipPart.isEmpty()) {
                return false
            }
        }
        ipParts.forEach { ipPart ->
            try {
                ipPart.toInt(16)
            } catch (e: NumberFormatException) {
                return false
            }
        }
        return true
    }
}