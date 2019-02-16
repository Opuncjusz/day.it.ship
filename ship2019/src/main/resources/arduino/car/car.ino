#define in1 2
#define in2 4
#define enA 3

#define in3 5
#define in4 7
#define enB 6

#define in5 8
#define in6 10
#define enC 9

#define in7 13
#define in8 12
#define enD 11

#define CONSOLE_READ_RETARDATION 4

int motorA[] = {in1, in2, enA};
int motorB[] = {in3, in4, enB};
int motorC[] = {in5, in6, enC};
int motorD[] = {in7, in8, enD};

void initializeSerial() {
  Serial.begin(9600);
}

void setup() {
  initializeSerial();
  
  pinMode(enA, OUTPUT);
  pinMode(enB, OUTPUT);
  pinMode(enC, OUTPUT);
  pinMode(enD, OUTPUT);
  
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
  
  pinMode(in5, OUTPUT);
  pinMode(in6, OUTPUT);
  pinMode(in7, OUTPUT);
  pinMode(in8, OUTPUT);
}


void loop() {
  if(Serial) {
    if(Serial.available()) {
      handleMotor(motorA);
      handleMotor(motorB);
      handleMotor(motorC);
      handleMotor(motorD);
    }
  } else {
    delay(1000);
    initializeSerial();
  }
}

void handleMotor(int motor[]) {
  int sign = readSign();
  if(sign) {
    int speed = readNumber();
    moveMotor(motor, sign == 2, speed);
  }
}

int readSign(){
  delay(CONSOLE_READ_RETARDATION);
  char input = Serial.read();
  if(input == '+'){
    return 2;
  } else if(input == '-') {
    return 1;
  } else {
    return 0;
  }
} 

int readNumber() {
  delay(CONSOLE_READ_RETARDATION);
  int a1 = (Serial.read() - '0') * 100;
  delay(CONSOLE_READ_RETARDATION);
  int a2 = (Serial.read() - '0') * 10;
  delay(CONSOLE_READ_RETARDATION);
  int a3 = (Serial.read() - '0') * 1;
  return a1 + a2 + a3;
}

void moveMotor(int motor[], boolean forward, int speed) {
  if(forward) {
    digitalWrite(motor[0], HIGH);
    digitalWrite(motor[1], LOW);
  } else {
    digitalWrite(motor[0], LOW);
    digitalWrite(motor[1], HIGH);
  }
  analogWrite(motor[2], speed);
}
