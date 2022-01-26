package unsw.gloriaromanus.mode;

public interface Subject {
    public void subscribe(Observer newObs);
    public void unsubscribe(Observer newObs);
    public void notifier(BasicCampaign game);
}
