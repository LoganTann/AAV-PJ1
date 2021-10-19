package resolver;

import sac.Bagpack;
import sac.Item;

import java.util.List;

public interface ResolverInterface {
    /**
     * Interface résolvant le problème du sac à dos (de quelque manière que ce soit)
     * @param bag le sac à dos
     * @param objects les objets potentiels à mettre dedans
     */
    void solveProblem(Bagpack bag, List<Item> objects);
}
