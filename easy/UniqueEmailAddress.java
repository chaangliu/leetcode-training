package easy;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddress {
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) return 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < emails.length; i++) {
            String[] paris = emails[i].split("@");
            StringBuilder user = new StringBuilder();
            for (int j = 0; j < paris[0].length(); j++) {
                if (paris[0].charAt(j) == '+') break;
                if (paris[0].charAt(j) != '.') {
                    user.append(paris[0].charAt(j));
                }
            }
            set.add(user + "@" + paris[1]);
        }
        return set.size();
    }

    public static void main(String args[]) {
        String[] s = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        int res = new UniqueEmailAddress().numUniqueEmails(s);
        int a = new UniqueEmailAddress().numUniqueEmails(s);

    }
}
