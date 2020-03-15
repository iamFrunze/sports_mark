package com.byfrunze.sportsball.logicapp;

import com.byfrunze.sportsball.models.ModelOfResultsMarks;
import com.byfrunze.sportsball.models.ModelWarriorMark;

public class MathemLogic {
    private ModelWarriorMark modelWarriorMark;

    public MathemLogic(ModelWarriorMark modelWarriorMark) {
        this.modelWarriorMark = modelWarriorMark;
    }

    private final String radio3 = "RB3";
    private final String radio4 = "RB4";
    private final String radio5 = "RB5";


    private boolean minPoints(int ageCategory) {
        switch (ageCategory) {
            case 1:
            case 102:
                return modelWarriorMark.getStrength() >= 30 &&
                        modelWarriorMark.getSpeed() >= 30 &&
                        modelWarriorMark.getStamina() >= 30 &&
                        modelWarriorMark.getWs() >= 30 &&
                        modelWarriorMark.getAgility() >= 30;
            case 2:
            case 11:
            case 101:
                return modelWarriorMark.getStrength() >= 28 &&
                        modelWarriorMark.getSpeed() >= 28 &&
                        modelWarriorMark.getStamina() >= 28 &&
                        modelWarriorMark.getWs() >= 28 &&
                        modelWarriorMark.getAgility() >= 28;
            case 3:
            case 13:
                return modelWarriorMark.getStrength() >= 24 &&
                        modelWarriorMark.getSpeed() >= 24 &&
                        modelWarriorMark.getStamina() >= 24 &&
                        modelWarriorMark.getWs() >= 24 &&
                        modelWarriorMark.getAgility() >= 24;
            case 4:
            case 14:
                return modelWarriorMark.getStrength() >= 22 &&
                        modelWarriorMark.getSpeed() >= 22 &&
                        modelWarriorMark.getStamina() >= 22 &&
                        modelWarriorMark.getWs() >= 22 &&
                        modelWarriorMark.getAgility() >= 22;
            case 5:
            case 15:
                return modelWarriorMark.getStrength() >= 20 &&
                        modelWarriorMark.getSpeed() >= 20 &&
                        modelWarriorMark.getStamina() >= 20 &&
                        modelWarriorMark.getWs() >= 20 &&
                        modelWarriorMark.getAgility() >= 20;
            case 6:
                return modelWarriorMark.getStrength() >= 16 &&
                        modelWarriorMark.getSpeed() >= 16 &&
                        modelWarriorMark.getStamina() >= 16 &&
                        modelWarriorMark.getWs() >= 16 &&
                        modelWarriorMark.getAgility() >= 16;
            case 7:
                return modelWarriorMark.getStrength() >= 12 &&
                        modelWarriorMark.getSpeed() >= 12 &&
                        modelWarriorMark.getStamina() >= 12 &&
                        modelWarriorMark.getWs() >= 12 &&
                        modelWarriorMark.getAgility() >= 12;
            case 8:
                return modelWarriorMark.getStrength() >= 6 &&
                        modelWarriorMark.getSpeed() >= 6 &&
                        modelWarriorMark.getStamina() >= 6 &&
                        modelWarriorMark.getWs() >= 6 &&
                        modelWarriorMark.getAgility() >= 6;
            case 12:
                return modelWarriorMark.getStrength() >= 26 &&
                        modelWarriorMark.getSpeed() >= 26 &&
                        modelWarriorMark.getStamina() >= 26 &&
                        modelWarriorMark.getWs() >= 26 &&
                        modelWarriorMark.getAgility() >= 26;
            case 16:
                return modelWarriorMark.getStrength() >= 18 &&
                        modelWarriorMark.getSpeed() >= 18 &&
                        modelWarriorMark.getStamina() >= 18 &&
                        modelWarriorMark.getWs() >= 18 &&
                        modelWarriorMark.getAgility() >= 18;
            case 103:
                return modelWarriorMark.getStrength() >= 32 &&
                        modelWarriorMark.getSpeed() >= 32 &&
                        modelWarriorMark.getStamina() >= 32 &&
                        modelWarriorMark.getWs() >= 32 &&
                        modelWarriorMark.getAgility() >= 32;
            default:
                return false;
        }


    }

    private String mark(ModelOfResultsMarks model, int ageCategory) {
        if (model.getResultsPoint() >= model.getMaxLvl() && minPoints(ageCategory))
            return "Высший уровень";
        else if (model.getResultsPoint() < model.getMaxLvl() && model.getResultsPoint() >= model.getLvl_1() && minPoints(ageCategory))
            return "5 (1 Уровень)";
        else if (model.getResultsPoint() < model.getLvl_1() && model.getResultsPoint() >= model.getLvl_2() && minPoints(ageCategory))
            return "5 (2 Уровень)";
        else if (model.getResultsPoint() < model.getLvl_2() && model.getResultsPoint() >= model.getLvl_3() && minPoints(ageCategory))
            return "5 (3 Уровень)";
        else if (model.getResultsPoint() < model.getMark_5() && model.getResultsPoint() >= model.getMark_4() && minPoints(ageCategory))
            return "4";
        else if (model.getResultsPoint() < model.getMark_4() && model.getResultsPoint() >= model.getMark_3() && minPoints(ageCategory))
            return "3";
        else return "2";
    }

    private ModelOfResultsMarks firstAge(String radioChecked) {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();

        switch (radioChecked) {
            case radio3:

                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina());

                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(200);
                    modelOfResultsMarks.setMark_4(180);
                    modelOfResultsMarks.setMark_3(130);

                    modelOfResultsMarks.setMaxLvl(240);
                    modelOfResultsMarks.setLvl_1(230);
                    modelOfResultsMarks.setLvl_2(220);
                    modelOfResultsMarks.setLvl_3(200);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(210);
                    modelOfResultsMarks.setMark_4(190);
                    modelOfResultsMarks.setMark_3(140);

                    modelOfResultsMarks.setMaxLvl(250);
                    modelOfResultsMarks.setLvl_1(240);
                    modelOfResultsMarks.setLvl_2(230);
                    modelOfResultsMarks.setLvl_3(210);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(220);
                    modelOfResultsMarks.setMark_4(200);
                    modelOfResultsMarks.setMark_3(140);

                    modelOfResultsMarks.setMaxLvl(260);
                    modelOfResultsMarks.setLvl_1(250);
                    modelOfResultsMarks.setLvl_2(240);
                    modelOfResultsMarks.setLvl_3(220);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;
                }
            case radio4:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(270);
                    modelOfResultsMarks.setMark_4(240);
                    modelOfResultsMarks.setMark_3(170);

                    modelOfResultsMarks.setMaxLvl(310);
                    modelOfResultsMarks.setLvl_1(300);
                    modelOfResultsMarks.setLvl_2(290);
                    modelOfResultsMarks.setLvl_3(270);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(280);
                    modelOfResultsMarks.setMark_4(250);
                    modelOfResultsMarks.setMark_3(180);

                    modelOfResultsMarks.setMaxLvl(320);
                    modelOfResultsMarks.setLvl_1(310);
                    modelOfResultsMarks.setLvl_2(300);
                    modelOfResultsMarks.setLvl_3(280);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(290);
                    modelOfResultsMarks.setMark_4(260);
                    modelOfResultsMarks.setMark_3(190);

                    modelOfResultsMarks.setMaxLvl(330);
                    modelOfResultsMarks.setLvl_1(320);
                    modelOfResultsMarks.setLvl_2(310);
                    modelOfResultsMarks.setLvl_3(290);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;
                }
            case radio5:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs() +
                        modelWarriorMark.getAgility());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(340);
                    modelOfResultsMarks.setMark_4(300);
                    modelOfResultsMarks.setMark_3(220);

                    modelOfResultsMarks.setMaxLvl(380);
                    modelOfResultsMarks.setLvl_1(370);
                    modelOfResultsMarks.setLvl_2(360);
                    modelOfResultsMarks.setLvl_3(340);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(350);
                    modelOfResultsMarks.setMark_4(310);
                    modelOfResultsMarks.setMark_3(230);

                    modelOfResultsMarks.setMaxLvl(390);
                    modelOfResultsMarks.setLvl_1(380);
                    modelOfResultsMarks.setLvl_2(370);
                    modelOfResultsMarks.setLvl_3(350);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(360);
                    modelOfResultsMarks.setMark_4(330);
                    modelOfResultsMarks.setMark_3(250);

                    modelOfResultsMarks.setMaxLvl(400);
                    modelOfResultsMarks.setLvl_1(390);
                    modelOfResultsMarks.setLvl_2(380);
                    modelOfResultsMarks.setLvl_3(360);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 1));
                    return modelOfResultsMarks;
                }
            default:
                return null;
        }
    }

    private ModelOfResultsMarks secondAge(String radioChecked) {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();

        switch (radioChecked) {
            case radio3:

                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina());

                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(180);
                    modelOfResultsMarks.setMark_4(160);
                    modelOfResultsMarks.setMark_3(120);

                    modelOfResultsMarks.setMaxLvl(220);
                    modelOfResultsMarks.setLvl_1(210);
                    modelOfResultsMarks.setLvl_2(200);
                    modelOfResultsMarks.setLvl_3(180);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(190);
                    modelOfResultsMarks.setMark_4(170);
                    modelOfResultsMarks.setMark_3(130);

                    modelOfResultsMarks.setMaxLvl(230);
                    modelOfResultsMarks.setLvl_1(220);
                    modelOfResultsMarks.setLvl_2(210);
                    modelOfResultsMarks.setLvl_3(190);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(200);
                    modelOfResultsMarks.setMark_4(180);
                    modelOfResultsMarks.setMark_3(140);

                    modelOfResultsMarks.setMaxLvl(240);
                    modelOfResultsMarks.setLvl_1(230);
                    modelOfResultsMarks.setLvl_2(220);
                    modelOfResultsMarks.setLvl_3(200);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;
                }
            case radio4:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(240);
                    modelOfResultsMarks.setMark_4(210);
                    modelOfResultsMarks.setMark_3(160);

                    modelOfResultsMarks.setMaxLvl(280);
                    modelOfResultsMarks.setLvl_1(270);
                    modelOfResultsMarks.setLvl_2(260);
                    modelOfResultsMarks.setLvl_3(240);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(250);
                    modelOfResultsMarks.setMark_4(220);
                    modelOfResultsMarks.setMark_3(170);

                    modelOfResultsMarks.setMaxLvl(290);
                    modelOfResultsMarks.setLvl_1(280);
                    modelOfResultsMarks.setLvl_2(270);
                    modelOfResultsMarks.setLvl_3(250);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(260);
                    modelOfResultsMarks.setMark_4(230);
                    modelOfResultsMarks.setMark_3(180);

                    modelOfResultsMarks.setMaxLvl(300);
                    modelOfResultsMarks.setLvl_1(290);
                    modelOfResultsMarks.setLvl_2(280);
                    modelOfResultsMarks.setLvl_3(260);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;
                }
            case radio5:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs() +
                        modelWarriorMark.getAgility());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(300);
                    modelOfResultsMarks.setMark_4(270);
                    modelOfResultsMarks.setMark_3(200);

                    modelOfResultsMarks.setMaxLvl(340);
                    modelOfResultsMarks.setLvl_1(330);
                    modelOfResultsMarks.setLvl_2(320);
                    modelOfResultsMarks.setLvl_3(300);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(310);
                    modelOfResultsMarks.setMark_4(280);
                    modelOfResultsMarks.setMark_3(210);

                    modelOfResultsMarks.setMaxLvl(350);
                    modelOfResultsMarks.setLvl_1(340);
                    modelOfResultsMarks.setLvl_2(330);
                    modelOfResultsMarks.setLvl_3(310);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(320);
                    modelOfResultsMarks.setMark_4(290);
                    modelOfResultsMarks.setMark_3(210);

                    modelOfResultsMarks.setMaxLvl(360);
                    modelOfResultsMarks.setLvl_1(350);
                    modelOfResultsMarks.setLvl_2(340);
                    modelOfResultsMarks.setLvl_3(320);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 2));
                    return modelOfResultsMarks;
                }
            default:
                return null;
        }
    }

    private ModelOfResultsMarks thirdAge(String radioChecked) {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();

        switch (radioChecked) {
            case radio3:

                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina());

                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(160);
                    modelOfResultsMarks.setMark_4(140);
                    modelOfResultsMarks.setMark_3(100);

                    modelOfResultsMarks.setMaxLvl(190);
                    modelOfResultsMarks.setLvl_1(180);
                    modelOfResultsMarks.setLvl_2(170);
                    modelOfResultsMarks.setLvl_3(160);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(170);
                    modelOfResultsMarks.setMark_4(150);
                    modelOfResultsMarks.setMark_3(110);

                    modelOfResultsMarks.setMaxLvl(200);
                    modelOfResultsMarks.setLvl_1(190);
                    modelOfResultsMarks.setLvl_2(180);
                    modelOfResultsMarks.setLvl_3(170);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(180);
                    modelOfResultsMarks.setMark_4(160);
                    modelOfResultsMarks.setMark_3(120);

                    modelOfResultsMarks.setMaxLvl(220);
                    modelOfResultsMarks.setLvl_1(210);
                    modelOfResultsMarks.setLvl_2(200);
                    modelOfResultsMarks.setLvl_3(180);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;
                }
            case radio4:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(220);
                    modelOfResultsMarks.setMark_4(190);
                    modelOfResultsMarks.setMark_3(140);

                    modelOfResultsMarks.setMaxLvl(250);
                    modelOfResultsMarks.setLvl_1(240);
                    modelOfResultsMarks.setLvl_2(230);
                    modelOfResultsMarks.setLvl_3(220);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(230);
                    modelOfResultsMarks.setMark_4(200);
                    modelOfResultsMarks.setMark_3(150);

                    modelOfResultsMarks.setMaxLvl(260);
                    modelOfResultsMarks.setLvl_1(250);
                    modelOfResultsMarks.setLvl_2(240);
                    modelOfResultsMarks.setLvl_3(230);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(240);
                    modelOfResultsMarks.setMark_4(210);
                    modelOfResultsMarks.setMark_3(160);

                    modelOfResultsMarks.setMaxLvl(270);
                    modelOfResultsMarks.setLvl_1(260);
                    modelOfResultsMarks.setLvl_2(250);
                    modelOfResultsMarks.setLvl_3(240);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;
                }
            case radio5:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs() +
                        modelWarriorMark.getAgility());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(250);
                    modelOfResultsMarks.setMark_4(220);
                    modelOfResultsMarks.setMark_3(150);

                    modelOfResultsMarks.setMaxLvl(280);
                    modelOfResultsMarks.setLvl_1(270);
                    modelOfResultsMarks.setLvl_2(260);
                    modelOfResultsMarks.setLvl_3(250);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(270);
                    modelOfResultsMarks.setMark_4(240);
                    modelOfResultsMarks.setMark_3(170);

                    modelOfResultsMarks.setMaxLvl(300);
                    modelOfResultsMarks.setLvl_1(290);
                    modelOfResultsMarks.setLvl_2(280);
                    modelOfResultsMarks.setLvl_3(270);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(290);
                    modelOfResultsMarks.setMark_4(250);
                    modelOfResultsMarks.setMark_3(190);

                    modelOfResultsMarks.setMaxLvl(320);
                    modelOfResultsMarks.setLvl_1(310);
                    modelOfResultsMarks.setLvl_2(300);
                    modelOfResultsMarks.setLvl_3(290);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 3));
                    return modelOfResultsMarks;
                }
            default:
                return null;
        }
    }

    private ModelOfResultsMarks fourAge(String radioChecked) {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();

        switch (radioChecked) {
            case radio3:

                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina());

                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(150);
                    modelOfResultsMarks.setMark_4(130);
                    modelOfResultsMarks.setMark_3(80);

                    modelOfResultsMarks.setMaxLvl(180);
                    modelOfResultsMarks.setLvl_1(170);
                    modelOfResultsMarks.setLvl_2(160);
                    modelOfResultsMarks.setLvl_3(150);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(160);
                    modelOfResultsMarks.setMark_4(140);
                    modelOfResultsMarks.setMark_3(190);

                    modelOfResultsMarks.setMaxLvl(190);
                    modelOfResultsMarks.setLvl_1(180);
                    modelOfResultsMarks.setLvl_2(170);
                    modelOfResultsMarks.setLvl_3(160);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(170);
                    modelOfResultsMarks.setMark_4(150);
                    modelOfResultsMarks.setMark_3(100);

                    modelOfResultsMarks.setMaxLvl(210);
                    modelOfResultsMarks.setLvl_1(200);
                    modelOfResultsMarks.setLvl_2(190);
                    modelOfResultsMarks.setLvl_3(170);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;
                }
            case radio4:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(200);
                    modelOfResultsMarks.setMark_4(170);
                    modelOfResultsMarks.setMark_3(110);

                    modelOfResultsMarks.setMaxLvl(240);
                    modelOfResultsMarks.setLvl_1(230);
                    modelOfResultsMarks.setLvl_2(220);
                    modelOfResultsMarks.setLvl_3(200);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(210);
                    modelOfResultsMarks.setMark_4(180);
                    modelOfResultsMarks.setMark_3(120);

                    modelOfResultsMarks.setMaxLvl(250);
                    modelOfResultsMarks.setLvl_1(240);
                    modelOfResultsMarks.setLvl_2(230);
                    modelOfResultsMarks.setLvl_3(210);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(230);
                    modelOfResultsMarks.setMark_4(200);
                    modelOfResultsMarks.setMark_3(140);

                    modelOfResultsMarks.setMaxLvl(260);
                    modelOfResultsMarks.setLvl_1(250);
                    modelOfResultsMarks.setLvl_2(240);
                    modelOfResultsMarks.setLvl_3(230);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;
                }
            case radio5:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs() +
                        modelWarriorMark.getAgility());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(230);
                    modelOfResultsMarks.setMark_4(200);
                    modelOfResultsMarks.setMark_3(130);

                    modelOfResultsMarks.setMaxLvl(280);
                    modelOfResultsMarks.setLvl_1(270);
                    modelOfResultsMarks.setLvl_2(260);
                    modelOfResultsMarks.setLvl_3(230);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(250);
                    modelOfResultsMarks.setMark_4(220);
                    modelOfResultsMarks.setMark_3(150);

                    modelOfResultsMarks.setMaxLvl(280);
                    modelOfResultsMarks.setLvl_1(270);
                    modelOfResultsMarks.setLvl_2(260);
                    modelOfResultsMarks.setLvl_3(250);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(270);
                    modelOfResultsMarks.setMark_4(230);
                    modelOfResultsMarks.setMark_3(170);

                    modelOfResultsMarks.setMaxLvl(300);
                    modelOfResultsMarks.setLvl_1(290);
                    modelOfResultsMarks.setLvl_2(280);
                    modelOfResultsMarks.setLvl_3(270);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 4));
                    return modelOfResultsMarks;
                }
            default:
                return null;
        }
    }

    private ModelOfResultsMarks fiveAge(String radioChecked) {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();

        switch (radioChecked) {
            case radio3:

                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina());

                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(120);
                    modelOfResultsMarks.setMark_4(90);
                    modelOfResultsMarks.setMark_3(70);

                    modelOfResultsMarks.setMaxLvl(160);
                    modelOfResultsMarks.setLvl_1(150);
                    modelOfResultsMarks.setLvl_2(140);
                    modelOfResultsMarks.setLvl_3(120);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 5));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(130);
                    modelOfResultsMarks.setMark_4(100);
                    modelOfResultsMarks.setMark_3(80);

                    modelOfResultsMarks.setMaxLvl(170);
                    modelOfResultsMarks.setLvl_1(160);
                    modelOfResultsMarks.setLvl_2(150);
                    modelOfResultsMarks.setLvl_3(130);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 5));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(140);
                    modelOfResultsMarks.setMark_4(120);
                    modelOfResultsMarks.setMark_3(90);

                    modelOfResultsMarks.setMaxLvl(180);
                    modelOfResultsMarks.setLvl_1(170);
                    modelOfResultsMarks.setLvl_2(160);
                    modelOfResultsMarks.setLvl_3(140);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 5));
                    return modelOfResultsMarks;
                }
            case radio4:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs());
                if (modelWarriorMark.getCategory() == 2) {

                    modelOfResultsMarks.setMark_5(160);
                    modelOfResultsMarks.setMark_4(120);
                    modelOfResultsMarks.setMark_3(100);

                    modelOfResultsMarks.setMaxLvl(200);
                    modelOfResultsMarks.setLvl_1(190);
                    modelOfResultsMarks.setLvl_2(180);
                    modelOfResultsMarks.setLvl_3(160);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 5));
                    return modelOfResultsMarks;
                } else if (modelWarriorMark.getCategory() == 1) {

                    modelOfResultsMarks.setMark_5(180);
                    modelOfResultsMarks.setMark_4(140);
                    modelOfResultsMarks.setMark_3(110);

                    modelOfResultsMarks.setMaxLvl(230);
                    modelOfResultsMarks.setLvl_1(210);
                    modelOfResultsMarks.setLvl_2(200);
                    modelOfResultsMarks.setLvl_3(180);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 5));
                    return modelOfResultsMarks;

                } else {

                    modelOfResultsMarks.setMark_5(190);
                    modelOfResultsMarks.setMark_4(160);
                    modelOfResultsMarks.setMark_3(120);

                    modelOfResultsMarks.setMaxLvl(230);
                    modelOfResultsMarks.setLvl_1(220);
                    modelOfResultsMarks.setLvl_2(210);
                    modelOfResultsMarks.setLvl_3(190);

                    modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 5));
                    return modelOfResultsMarks;
                }
            default:
                return null;
        }
    }

    private ModelOfResultsMarks sixAge() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());

        if (modelWarriorMark.getCategory() == 2) {

            modelOfResultsMarks.setMark_5(90);
            modelOfResultsMarks.setMark_4(60);
            modelOfResultsMarks.setMark_3(50);

            modelOfResultsMarks.setMaxLvl(130);
            modelOfResultsMarks.setLvl_1(120);
            modelOfResultsMarks.setLvl_2(110);
            modelOfResultsMarks.setLvl_3(90);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 6));
            return modelOfResultsMarks;
        } else if (modelWarriorMark.getCategory() == 1) {

            modelOfResultsMarks.setMark_5(100);
            modelOfResultsMarks.setMark_4(70);
            modelOfResultsMarks.setMark_3(60);

            modelOfResultsMarks.setMaxLvl(140);
            modelOfResultsMarks.setLvl_1(130);
            modelOfResultsMarks.setLvl_2(120);
            modelOfResultsMarks.setLvl_3(100);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 6));
            return modelOfResultsMarks;

        } else {

            modelOfResultsMarks.setMark_5(120);
            modelOfResultsMarks.setMark_4(90);
            modelOfResultsMarks.setMark_3(80);

            modelOfResultsMarks.setMaxLvl(160);
            modelOfResultsMarks.setLvl_1(150);
            modelOfResultsMarks.setLvl_2(140);
            modelOfResultsMarks.setLvl_3(120);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 6));
            return modelOfResultsMarks;
        }

    }

    private ModelOfResultsMarks sevenAge() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());

        if (modelWarriorMark.getCategory() == 2) {

            modelOfResultsMarks.setMark_5(80);
            modelOfResultsMarks.setMark_4(50);
            modelOfResultsMarks.setMark_3(40);

            modelOfResultsMarks.setMaxLvl(120);
            modelOfResultsMarks.setLvl_1(110);
            modelOfResultsMarks.setLvl_2(100);
            modelOfResultsMarks.setLvl_3(80);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 7));
            return modelOfResultsMarks;
        } else if (modelWarriorMark.getCategory() == 1) {

            modelOfResultsMarks.setMark_5(90);
            modelOfResultsMarks.setMark_4(60);
            modelOfResultsMarks.setMark_3(50);

            modelOfResultsMarks.setMaxLvl(130);
            modelOfResultsMarks.setLvl_1(120);
            modelOfResultsMarks.setLvl_2(110);
            modelOfResultsMarks.setLvl_3(90);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 7));
            return modelOfResultsMarks;

        } else {

            modelOfResultsMarks.setMark_5(110);
            modelOfResultsMarks.setMark_4(80);
            modelOfResultsMarks.setMark_3(70);

            modelOfResultsMarks.setMaxLvl(150);
            modelOfResultsMarks.setLvl_1(140);
            modelOfResultsMarks.setLvl_2(130);
            modelOfResultsMarks.setLvl_3(110);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 7));
            return modelOfResultsMarks;
        }

    }

    private ModelOfResultsMarks eightAge() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());

        if (modelWarriorMark.getCategory() == 2) {

            modelOfResultsMarks.setMark_5(70);
            modelOfResultsMarks.setMark_4(50);
            modelOfResultsMarks.setMark_3(30);

            modelOfResultsMarks.setMaxLvl(110);
            modelOfResultsMarks.setLvl_1(100);
            modelOfResultsMarks.setLvl_2(90);
            modelOfResultsMarks.setLvl_3(70);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 8));
            return modelOfResultsMarks;
        } else if (modelWarriorMark.getCategory() == 1) {

            modelOfResultsMarks.setMark_5(80);
            modelOfResultsMarks.setMark_4(60);
            modelOfResultsMarks.setMark_3(40);

            modelOfResultsMarks.setMaxLvl(120);
            modelOfResultsMarks.setLvl_1(110);
            modelOfResultsMarks.setLvl_2(100);
            modelOfResultsMarks.setLvl_3(80);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 8));
            return modelOfResultsMarks;

        } else {

            modelOfResultsMarks.setMark_5(100);
            modelOfResultsMarks.setMark_4(80);
            modelOfResultsMarks.setMark_3(60);

            modelOfResultsMarks.setMaxLvl(140);
            modelOfResultsMarks.setLvl_1(130);
            modelOfResultsMarks.setLvl_2(120);
            modelOfResultsMarks.setLvl_3(100);

            modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 8));
            return modelOfResultsMarks;
        }

    }

    public ModelOfResultsMarks setupCategories(String radioChecked) {
        if (modelWarriorMark.getAge() < 25) return firstAge(radioChecked);
        if (modelWarriorMark.getAge() >= 25 && modelWarriorMark.getAge() < 30)
            return secondAge(radioChecked);
        if (modelWarriorMark.getAge() >= 30 && modelWarriorMark.getAge() < 35)
            return thirdAge(radioChecked);
        if (modelWarriorMark.getAge() >= 35 && modelWarriorMark.getAge() < 40)
            return fourAge(radioChecked);
        if (modelWarriorMark.getAge() >= 40 && modelWarriorMark.getAge() < 45)
            return fiveAge(radioChecked);
        if (modelWarriorMark.getAge() >= 45 && modelWarriorMark.getAge() < 50) return sixAge();
        if (modelWarriorMark.getAge() >= 50 && modelWarriorMark.getAge() < 55) return sevenAge();
        if (modelWarriorMark.getAge() >= 55) return eightAge();
        else return null;

    }

    private ModelOfResultsMarks oneAgeW() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());


        modelOfResultsMarks.setMark_5(160);
        modelOfResultsMarks.setMark_4(140);
        modelOfResultsMarks.setMark_3(110);

        modelOfResultsMarks.setMaxLvl(200);
        modelOfResultsMarks.setLvl_1(190);
        modelOfResultsMarks.setLvl_2(180);
        modelOfResultsMarks.setLvl_3(160);

        modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 11));
        return modelOfResultsMarks;
    }

    private ModelOfResultsMarks twoAgeW() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());


        modelOfResultsMarks.setMark_5(140);
        modelOfResultsMarks.setMark_4(120);
        modelOfResultsMarks.setMark_3(90);

        modelOfResultsMarks.setMaxLvl(180);
        modelOfResultsMarks.setLvl_1(170);
        modelOfResultsMarks.setLvl_2(160);
        modelOfResultsMarks.setLvl_3(140);

        modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 12));
        return modelOfResultsMarks;
    }

    private ModelOfResultsMarks threeAgeW() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());


        modelOfResultsMarks.setMark_5(130);
        modelOfResultsMarks.setMark_4(110);
        modelOfResultsMarks.setMark_3(80);

        modelOfResultsMarks.setMaxLvl(170);
        modelOfResultsMarks.setLvl_1(160);
        modelOfResultsMarks.setLvl_2(150);
        modelOfResultsMarks.setLvl_3(130);

        modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 13));
        return modelOfResultsMarks;
    }

    private ModelOfResultsMarks fourAgeW() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());


        modelOfResultsMarks.setMark_5(120);
        modelOfResultsMarks.setMark_4(100);
        modelOfResultsMarks.setMark_3(70);

        modelOfResultsMarks.setMaxLvl(160);
        modelOfResultsMarks.setLvl_1(150);
        modelOfResultsMarks.setLvl_2(140);
        modelOfResultsMarks.setLvl_3(120);

        modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 14));
        return modelOfResultsMarks;
    }

    private ModelOfResultsMarks fiveAgeW() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());


        modelOfResultsMarks.setMark_5(110);
        modelOfResultsMarks.setMark_4(90);
        modelOfResultsMarks.setMark_3(60);

        modelOfResultsMarks.setMaxLvl(150);
        modelOfResultsMarks.setLvl_1(140);
        modelOfResultsMarks.setLvl_2(130);
        modelOfResultsMarks.setLvl_3(110);

        modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 15));
        return modelOfResultsMarks;
    }

    private ModelOfResultsMarks sixAgeW() {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();


        modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                modelWarriorMark.getSpeed() +
                modelWarriorMark.getStamina());


        modelOfResultsMarks.setMark_5(100);
        modelOfResultsMarks.setMark_4(80);
        modelOfResultsMarks.setMark_3(50);

        modelOfResultsMarks.setMaxLvl(140);
        modelOfResultsMarks.setLvl_1(130);
        modelOfResultsMarks.setLvl_2(120);
        modelOfResultsMarks.setLvl_3(100);

        modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 16));
        return modelOfResultsMarks;
    }

    public ModelOfResultsMarks setupCategoriesWoman() {
        if (modelWarriorMark.getAge() < 25) return oneAgeW();
        if (modelWarriorMark.getAge() >= 25 && modelWarriorMark.getAge() < 30)
            return twoAgeW();
        if (modelWarriorMark.getAge() >= 30 && modelWarriorMark.getAge() < 35)
            return threeAgeW();
        if (modelWarriorMark.getAge() >= 35 && modelWarriorMark.getAge() < 40)
            return fourAgeW();
        if (modelWarriorMark.getAge() >= 40 && modelWarriorMark.getAge() < 45)
            return fiveAgeW();
        if (modelWarriorMark.getAge() >= 45) return sixAgeW();
        else return null;
    }

    public ModelOfResultsMarks setupCadet(String radioChecked, int course_id){
        if(course_id == 20) return oneCourse(radioChecked);
        else if(course_id == 21) return twoCourse(radioChecked);
        else return threeCourse(radioChecked);
    }

    private ModelOfResultsMarks oneCourse(String radioChecked) {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();

        switch (radioChecked) {
            case radio3:

                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina());

                modelOfResultsMarks.setMark_5(180);
                modelOfResultsMarks.setMark_4(160);
                modelOfResultsMarks.setMark_3(120);

                modelOfResultsMarks.setMaxLvl(220);
                modelOfResultsMarks.setLvl_1(210);
                modelOfResultsMarks.setLvl_2(200);
                modelOfResultsMarks.setLvl_3(180);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 101));
                return modelOfResultsMarks;
            case radio4:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs());

                modelOfResultsMarks.setMark_5(240);
                modelOfResultsMarks.setMark_4(220);
                modelOfResultsMarks.setMark_3(170);

                modelOfResultsMarks.setMaxLvl(280);
                modelOfResultsMarks.setLvl_1(270);
                modelOfResultsMarks.setLvl_2(260);
                modelOfResultsMarks.setLvl_3(240);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 101));
                return modelOfResultsMarks;
            case radio5:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs() +
                        modelWarriorMark.getAgility());

                modelOfResultsMarks.setMark_5(300);
                modelOfResultsMarks.setMark_4(260);
                modelOfResultsMarks.setMark_3(220);

                modelOfResultsMarks.setMaxLvl(330);
                modelOfResultsMarks.setLvl_1(320);
                modelOfResultsMarks.setLvl_2(310);
                modelOfResultsMarks.setLvl_3(300);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 101));
                return modelOfResultsMarks;
            default:
                return null;
        }
    }

    private ModelOfResultsMarks twoCourse(String radioChecked) {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();

        switch (radioChecked) {
            case radio3:

                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina());

                modelOfResultsMarks.setMark_5(190);
                modelOfResultsMarks.setMark_4(170);
                modelOfResultsMarks.setMark_3(130);

                modelOfResultsMarks.setMaxLvl(230);
                modelOfResultsMarks.setLvl_1(220);
                modelOfResultsMarks.setLvl_2(210);
                modelOfResultsMarks.setLvl_3(190);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 102));
                return modelOfResultsMarks;
            case radio4:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs());

                modelOfResultsMarks.setMark_5(250);
                modelOfResultsMarks.setMark_4(230);
                modelOfResultsMarks.setMark_3(190);

                modelOfResultsMarks.setMaxLvl(290);
                modelOfResultsMarks.setLvl_1(280);
                modelOfResultsMarks.setLvl_2(270);
                modelOfResultsMarks.setLvl_3(250);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 102));
                return modelOfResultsMarks;
            case radio5:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs() +
                        modelWarriorMark.getAgility());

                modelOfResultsMarks.setMark_5(310);
                modelOfResultsMarks.setMark_4(270);
                modelOfResultsMarks.setMark_3(230);

                modelOfResultsMarks.setMaxLvl(340);
                modelOfResultsMarks.setLvl_1(330);
                modelOfResultsMarks.setLvl_2(320);
                modelOfResultsMarks.setLvl_3(210);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 102));
                return modelOfResultsMarks;
            default:
                return null;
        }
    }

    private ModelOfResultsMarks threeCourse(String radioChecked) {
        ModelOfResultsMarks modelOfResultsMarks = new ModelOfResultsMarks();

        switch (radioChecked) {
            case radio3:

                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina());

                modelOfResultsMarks.setMark_5(210);
                modelOfResultsMarks.setMark_4(190);
                modelOfResultsMarks.setMark_3(150);

                modelOfResultsMarks.setMaxLvl(250);
                modelOfResultsMarks.setLvl_1(240);
                modelOfResultsMarks.setLvl_2(230);
                modelOfResultsMarks.setLvl_3(210);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 103));
                return modelOfResultsMarks;
            case radio4:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs());

                modelOfResultsMarks.setMark_5(280);
                modelOfResultsMarks.setMark_4(250);
                modelOfResultsMarks.setMark_3(200);

                modelOfResultsMarks.setMaxLvl(320);
                modelOfResultsMarks.setLvl_1(310);
                modelOfResultsMarks.setLvl_2(300);
                modelOfResultsMarks.setLvl_3(280);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 103));
                return modelOfResultsMarks;
            case radio5:
                modelOfResultsMarks.setResultsPoint(modelWarriorMark.getStrength() +
                        modelWarriorMark.getSpeed() +
                        modelWarriorMark.getStamina() +
                        modelWarriorMark.getWs() +
                        modelWarriorMark.getAgility());

                modelOfResultsMarks.setMark_5(350);
                modelOfResultsMarks.setMark_4(310);
                modelOfResultsMarks.setMark_3(250);

                modelOfResultsMarks.setMaxLvl(390);
                modelOfResultsMarks.setLvl_1(380);
                modelOfResultsMarks.setLvl_2(370);
                modelOfResultsMarks.setLvl_3(350);

                modelOfResultsMarks.setCurrentMark(mark(modelOfResultsMarks, 103));
                return modelOfResultsMarks;
            default:
                return null;
        }
    }
}
