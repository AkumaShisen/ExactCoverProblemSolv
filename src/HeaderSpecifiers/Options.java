package HeaderSpecifiers;

import DancingLinkAlg.Identity;
import DancingLinkAlg.MainNode;

import java.util.List;

public interface Options {
    void addOptionsToMatrix(MainNode root);
    List<Identity> getIdentityList();
}
