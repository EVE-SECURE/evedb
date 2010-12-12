package lv.odylab.evedb.domain;

import java.util.Comparator;
import java.util.List;

public class InvTypeNameComparator implements Comparator<InvType> {

    @Override
    public int compare(InvType first, InvType second) {
        List<String> firstTypeNameTokens = first.getTypeNameTokens();
        List<String> secondTypeNameTokens = second.getTypeNameTokens();
        for (int i = 0; i < firstTypeNameTokens.size(); i++) {
            String token1 = firstTypeNameTokens.get(i);
            if (secondTypeNameTokens.size() >= i + 1) {
                String token2 = secondTypeNameTokens.get(i);
                if (!token1.equals(token2)) {
                    return token1.compareTo(token2);
                }
            } else {
                return 1;
            }
        }
        if (firstTypeNameTokens.size() < secondTypeNameTokens.size()) {
            return -1;
        }
        return 0;
    }
}
