package sac;

public interface BasicItemInterface {
    void setWeight(float weight);
    void setValue(float value);
    float getWeight();
    float getValue();
    BasicItemInterface copy();
}
