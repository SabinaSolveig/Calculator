package com.example.calculator;

public class CalculatorLogics {

    private int curArg;
    private int firstArg;
    private int secondArg;
    private char operation;

    private State state;

    private int actionSelected;

    private enum State{
        FIRST_ARG_INPUT,
        SECOND_ARG_INPUT,
        RESULT_SHOW
    }

    public CalculatorLogics(){
        state = State.FIRST_ARG_INPUT;
    }

    public void onNumPressed(int buttonId){
        if(state == State.RESULT_SHOW){
            state = State.FIRST_ARG_INPUT;
            curArg = 0;
            firstArg = 0;
            secondArg = 0;
        }

        if(String.valueOf(curArg).length() < 10){
            switch (buttonId) {
                case R.id.button0:
                    if (curArg != 0){
                        currentArgAdd(0);;
                    }
                    break;
                case R.id.button1:
                    currentArgAdd(1);;
                    break;
                case R.id.button2:
                    currentArgAdd(2);;
                    break;
                case R.id.button3:
                    currentArgAdd(3);;
                    break;
                case R.id.button4:
                    currentArgAdd(4);;
                    break;
                case R.id.button5:
                    currentArgAdd(5);;
                    break;
                case R.id.button6:
                    currentArgAdd(6);;
                    break;
                case R.id.button7:
                    currentArgAdd(7);;
                    break;
                case R.id.button8:
                    currentArgAdd(8);
                    break;
                case R.id.button9:
                    currentArgAdd(9);
                    break;
            }
        }
    }

    public void currentArgAdd(int num){
        curArg = curArg * 10 + num;
    }

    public void onActionPressed(int actionId){
        if (actionId == R.id.buttonClear) {
            pressedClear();
        } else if (actionId == R.id.buttonBackSpace) {
            pressedBackSpace();
        } else if (actionId == R.id.buttonEqual && state == State.SECOND_ARG_INPUT) {
            pressedEqual();
        } else if (curArg != 0) {
            if (state == State.SECOND_ARG_INPUT){
                pressedEqual();
            }
            pressedOperation(actionId);
        }
    }

    public void pressedEqual(){
        secondArg = curArg;
        state = State.RESULT_SHOW;
        curArg = 0;

        switch(actionSelected){
            case R.id.buttonAdd:
                curArg = firstArg + secondArg;
                break;
            case R.id.buttonSub:
                curArg = firstArg - secondArg;
                break;
            case R.id.buttonMul:
                curArg = firstArg * secondArg;
                break;
            case R.id.buttonDiv:
                curArg = firstArg / secondArg;
                break;
        }
        actionSelected = 0;
    }

    public void pressedOperation(int actionId){

        state = State.SECOND_ARG_INPUT;
        firstArg = curArg;
        secondArg = 0;
        curArg = 0;

        switch (actionId){
            case R.id.buttonAdd:
                actionSelected = R.id.buttonAdd;
                operation = '+';
                break;
            case R.id.buttonSub:
                actionSelected = R.id.buttonSub;
                operation = '-';
                break;
            case R.id.buttonMul:
                actionSelected = R.id.buttonMul;
                operation = '*';
                break;
            case R.id.buttonDiv:
                actionSelected = R.id.buttonDiv;
                operation = '/';
                break;
        }
    }

    public void pressedClear(){
        state = State.FIRST_ARG_INPUT;
        curArg = 0;
        firstArg = 0;
        secondArg = 0;
    }

    public void pressedBackSpace(){
        if (curArg != 0){
            curArg = curArg / 10;
        }
    }

    public String getText(){
        return String.valueOf(curArg);
    }

    public String getOperation(){
        String result = "";
        if (state == State.SECOND_ARG_INPUT){
            result = String.valueOf(firstArg) + operation;
        }
        if (state == State.RESULT_SHOW){
            result = String.valueOf(firstArg) + operation + String.valueOf(secondArg) + "=";
        }
        return result;
    }
}