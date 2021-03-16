package com.example.calculator;

public class CalculatorLogics {

    private int firstArg;
    private int secondArg;

    private State state;

    private StringBuilder inputStr = new StringBuilder();
    private StringBuilder result = new StringBuilder();

    private int actionSelected;

    private enum State{
        firstArgInput,
        secondArgInput,
        resultShow
    }

    public CalculatorLogics(){
        state = State.firstArgInput;
    }

    public void onNumPressed(int buttonId){

        if(state == State.resultShow){
            state = State.firstArgInput;
            inputStr.setLength(0);
        }

        if(inputStr.length() < 10){
            switch (buttonId) {
                case R.id.button0:
                    if (inputStr.length() != 0){
                        inputStr.append("0");
                    }
                    break;
                case R.id.button1:
                    inputStr.append("1");
                    break;
                case R.id.button2:
                    inputStr.append("2");
                    break;
                case R.id.button3:
                    inputStr.append("3");
                    break;
                case R.id.button4:
                    inputStr.append("4");
                    break;
                case R.id.button5:
                    inputStr.append("5");
                    break;
                case R.id.button6:
                    inputStr.append("6");
                    break;
                case R.id.button7:
                    inputStr.append("7");
                    break;
                case R.id.button8:
                    inputStr.append("8");
                    break;
                case R.id.button9:
                    inputStr.append("9");
                    break;
            }
        }

    }

    public void onActionPressed(int actionId){

        if (actionId == R.id.buttonClear) {
            pressedClear();
        } else if (actionId == R.id.buttonBackSpace) {
            pressedBackSpace();
        } else if (actionId == R.id.buttonEqual && state == State.secondArgInput) {
            pressedEqual();
        } else if (inputStr.length() > 0 && state == State.firstArgInput) {
            pressedOperation(actionId);
        }
    }

    public void pressedEqual(){
        secondArg = Integer.parseInt(inputStr.toString());
        state = State.resultShow;
        inputStr.setLength(0);

        switch(actionSelected){
            case R.id.buttonAdd:
                inputStr.append(firstArg + secondArg);
                break;
            case R.id.buttonSub:
                inputStr.append(firstArg - secondArg);
                break;
            case R.id.buttonMul:
                inputStr.append(firstArg * secondArg);
                break;
            case R.id.buttonDiv:
                inputStr.append(firstArg / secondArg);
                break;
        }
        result.append(secondArg);
        result.append("=");
        actionSelected = 0;
    }

    public void pressedOperation(int actionId){

        if(state == State.resultShow){
            state = State.firstArgInput;
        }

        firstArg = Integer.parseInt(inputStr.toString());
        inputStr.setLength(0);

        if (state == State.firstArgInput){
            state = State.secondArgInput;

            result.setLength(0);
            result.append(firstArg);
        }

        switch (actionId){
            case R.id.buttonAdd:
                actionSelected = R.id.buttonAdd;
                result.append("+");
                break;
            case R.id.buttonSub:
                actionSelected = R.id.buttonSub;
                result.append("-");
                break;
            case R.id.buttonMul:
                actionSelected = R.id.buttonMul;
                result.append("*");
                break;
            case R.id.buttonDiv:
                actionSelected = R.id.buttonDiv;
                result.append("/");
                break;
        }
    }

    public void pressedClear(){
        state = State.firstArgInput;
        firstArg = 0;
        secondArg = 0;
        inputStr.setLength(0);
        result.setLength(0);
    }

    public void pressedBackSpace(){
        if (inputStr.length() > 0){
            inputStr.setLength(inputStr.length() - 1);
        }
    }

    public String getText(){
        return inputStr.toString();
    }

    public String getOperation(){
        return result.toString();
    }
}
