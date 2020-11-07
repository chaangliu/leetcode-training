import java.net.*;

class Solution {
    /**
     * 题意：判断给定string是IPV4还是IPV6还是Neither。
     * 解法：（官方题解）可以将地址分为多个块，然后逐块进行验证。
     * 仅当每个块都有效时，该地址才有效。这种方法称为 分治法。
     */
    public String validIPAddress(String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            return validateIPv4(IP);
        } else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return validateIPv6(IP);
        } else return "Neither";
    }

    public String validateIPv4(String IP) {
        String[] nums = IP.split("\\.", -1);
        for (String x : nums) {
            // Validate integer in range (0, 255):
            // 1. length of chunk is between 1 and 3
            if (x.length() == 0 || x.length() > 3) return "Neither";
            // 2. no extra leading zeros
            if (x.charAt(0) == '0' && x.length() != 1) return "Neither";
            // 3. only digits are allowed
            for (char ch : x.toCharArray()) {
                if (!Character.isDigit(ch)) return "Neither";
            }
            // 4. less than 255
            if (Integer.parseInt(x) > 255) return "Neither";
        }
        return "IPv4";
    }

    public String validateIPv6(String IP) {
        String[] nums = IP.split(":", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : nums) {
            // Validate hexadecimal in range (0, 2**16):
            // 1. at least one and not more than 4 hexdigits in one chunk
            if (x.length() == 0 || x.length() > 4) return "Neither";
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (Character ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    }


    /**
     * trick: 内置api
     */
    public String validIPAddress_(String IP) {
        try {
            return (InetAddress.getByName(IP) instanceof Inet6Address) ? "IPv6" : "IPv4";
        } catch (Exception e) {
        }
        return "Neither";
    }
}