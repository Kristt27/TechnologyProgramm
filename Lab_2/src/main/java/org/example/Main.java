package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
                // Создаем участников
        Participant[] participants = {
                        new Человек("Человек 1", 100, 2),
                        new Человек("Человек 2", 50, 1),
                        new Человек("Человек 3", 100, 3),
                        new Кот("Кот 1", 50, 1),
                        new Робот("Робот 1", 80, 1)
                };

                // Создаем препятствия
                Object[] obstacles = {
                        new БеговаяДорожка(60),
                        new Стена(2),
                        new БеговаяДорожка(120),
                        new Стена(5)
                };

                // Участники проходят препятствия
                for (Participant participant : participants) {
                    for (Object obstacle : obstacles) {
                        if (obstacle instanceof БеговаяДорожка) {
                            if (!participant.run(((БеговаяДорожка) obstacle).getLength())) {
                                break; // Участник не смог пройти - дальше не идет
                            }
                        } else if (obstacle instanceof Стена) {
                            if (!participant.jump(((Стена) obstacle).getHeight())) {
                                break; // Участник не смог пройти - дальше не идет
                            }
                        }
                    }


                }
            }
        }