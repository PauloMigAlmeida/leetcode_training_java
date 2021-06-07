package ctci.string;

public class P1_6 {

    public String compress(String str){
        var chars = new int[128];

        var sb = new StringBuilder();
        for(var i = 0; i < str.length(); i++){
            var curr = str.charAt(i);
            if(i != 0){
                var prev = str.charAt(i-1);
                if(prev != curr){
                    sb.append(prev);
                    if(chars[prev - 'A'] > 1)
                        sb.append(chars[prev - 'A']);

                    chars[prev - 'A'] = 0;
                }
            }
            chars[curr - 'A']++;

            if(i == str.length() -1){
                //last time
                sb.append(curr);
                if(chars[curr - 'A'] > 1)
                    sb.append(chars[curr - 'A']);
            }

        }

        var ret = sb.toString();
        return ret.length() < str.length() ? ret : str;
    }

    public static void main(String[] args) {
        var instance = new P1_6();
        assert instance.compress("aaabbcccaa").equals("a3b2c3a2");
        assert instance.compress("aaabbcCcaa").equals("a3b2cCca2");
        // return original string as this isn't smaller than the compressed
        assert instance.compress("AaabbcCcaa").equals("AaabbcCcaa");
    }
}
