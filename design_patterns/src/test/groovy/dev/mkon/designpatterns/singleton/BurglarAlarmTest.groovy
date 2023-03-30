package dev.mkon.designpatterns.singleton

import spock.lang.Specification

import java.lang.reflect.AccessibleObject
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException

class BurglarAlarmTest extends Specification {

    def "should have only one instance when multiple calls of getInstance"() {
        given:
        BurglarAlarm alarm = BurglarAlarm.getInstance(1234)

        when:
        BurglarAlarm alarm2 = BurglarAlarm.getInstance(4321)
        alarm.arm(1234);

        then:
        alarm == alarm2
        alarm2.isArmed()
        !alarm2.disarm(4321)
        alarm.isArmed()
        alarm2.isArmed()
    }

    def "constructor shouldn't be available using reflection"() {
        given:
        BurglarAlarm alarm = BurglarAlarm.getInstance(1234)

        when:
        Constructor<BurglarAlarm> constructor = BurglarAlarm.class.getDeclaredConstructor(int)
        constructor.setAccessible(true)
        BurglarAlarm alarm2 = constructor.newInstance(4321)

        then:
        thrown(InvocationTargetException)
        alarm != null
        alarm2 == null
    }
}
