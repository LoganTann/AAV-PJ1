package sac;

public class BasicItem implements BasicItemInterface {
    private float weight, value;

    public BasicItem(float weight, float value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public BasicItem copy() {
        return new BasicItem(this.weight, this.value);
    }

    @Override
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public float getValue() {
        return this.value;
    }

    public BasicItemInterface copySum(float weight, float value) {
        return new BasicItem(this.weight + weight, this.value + value);
    }
    public static BasicItemInterface copySum(BasicItemInterface it, float weight, float value) {
        return new BasicItem(it.getWeight() + weight, it.getValue() + value);
    }
}
