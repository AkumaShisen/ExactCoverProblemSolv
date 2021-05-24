package HeaderSpecifiers;

import DancingLinkAlg.Identity;
import DancingLinkAlg.MainNode;

import java.util.List;

public interface Constrains {
    void addConstrainsToMatrix(MainNode root);
    List<Identity> getIdentityList();
    Constrains setOptional();
}
