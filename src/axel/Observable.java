package axel;

/**
 * Created by axell on 01/12/15.
 */
public interface Observable {
    void addObservateur(Observateur observateur);

    void removeObservateur(Observateur observateur);

    void notifyObservateur();
}
