package ru.job4j.ood.isp;

/*
Машина может быть с коробкой автомат, поэтому метод transmission тут лишний
 */
interface Car {
    void brake();

    void drive(int road);

    void transmission(int gear);
}

/*
механическая коробка передач не управляет ручным тормозом
 */
interface ManualTransmission {
    void setGear(int gear);

    boolean handBrake();
}

/*
Анти-блокировачная система регулирует работу тормозов, но не управляет светом
 */
interface ABS {
    void start();

    void breakControl();

    boolean lights();
}