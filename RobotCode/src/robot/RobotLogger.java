package robot;
import subsystems.*;
import states.*;
import java.util.logging.*;

import java.io.IOException;

public class RobotLogger {
	private static final Logger ANYTHING_LOGGER = Logger.getLogger("Anything_Logger");
	private static final Logger COLOR_SENSOR_LOGGER = Logger.getLogger("Color_Sensor_Log");
	private static final Logger CAMERA_LOGGER = Logger.getLogger("Camera_Log");
	private static final Logger ULTRASONIC_SENSORS_LOGGER = Logger.getLogger("Ultrasonic_Sensors_Log");
	private static final Logger GYROSCOPE_LOGGER = Logger.getLogger("Gyroscope_Log");
	private static final Logger ROBOT_STATE_LOGGER = Logger.getLogger("Robot_State_Log");
	private static final Logger DRIVE_TRAIN_LOGGER = Logger.getLogger("Drive_Train_Logger");
	private static final Logger HOPPERS_LOGGER = Logger.getLogger("Hoppers_Log");
	private static final Logger SORTER_LOGGER = Logger.getLogger("Sorter_Log");
	private static final Logger CONVEYOR_LOGGER = Logger.getLogger("Conveyor_Log");
	private static final Logger[] ALL_LOGGERS = {ANYTHING_LOGGER, COLOR_SENSOR_LOGGER, CAMERA_LOGGER,
													ULTRASONIC_SENSORS_LOGGER, GYROSCOPE_LOGGER, ROBOT_STATE_LOGGER,
													DRIVE_TRAIN_LOGGER, HOPPERS_LOGGER, SORTER_LOGGER, CONVEYOR_LOGGER};

	private static final boolean isLoggingAnything = true;
	private static final boolean isLoggingColorSensor = true;
	private static final boolean isLoggingCamera = true;
	private static final boolean isLoggingUltrasonicSensors = true;
	private static final boolean isLoggingGyroscope = true;
	private static final boolean isLoggingRobotState = true;
	private static final boolean isLoggingDriveTrain = true;
	private static final boolean isLoggingHoppers = true;
	private static final boolean isLoggingSorter = true;
	private static final boolean isLoggingConveyor = true;

	private static final String ANYTHING_FILE = "Anything.log";
	private static final String COLOR_SENSOR_FILE = "ColorSensor.log";
	private static final String CAMERA_FILE = "Camera.log";
	private static final String ULTRASONIC_SENSORS_FILE = "Ultrasonic.log";
	private static final String GYROSCOPE_FILE = "Gyroscope.log";
	private static final String ROBOT_STATE_FILE = "RobotState.log";
	private static final String DRIVE_TRAIN_FILE = "DriveTrain.log";
	private static final String HOPPERS_FILE = "Hoppers.log";
	private static final String SORTER_FILE = "Sorter.log";
	private static final String CONVEYOR_FILE = "Conveyor.file";
	private static final String[] ALL_FILES = {ANYTHING_FILE, COLOR_SENSOR_FILE, CAMERA_FILE, 
												ULTRASONIC_SENSORS_FILE, GYROSCOPE_FILE, ROBOT_STATE_FILE,
												DRIVE_TRAIN_FILE, HOPPERS_FILE, SORTER_FILE, CONVEYOR_FILE};

	public RobotLogger() {
		//Sets up file handler(s)
		try {
			for (int i = 0; i < ALL_LOGGERS.length; i++) {
				FileHandler fileHandler = new FileHandler(ALL_FILES[i]);
				ALL_LOGGERS[i].addHandler(fileHandler);
			}
		} catch (IOException exception) {
            ANYTHING_LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
		}
	}

	public void logInputs(InputStateVariables input) {
		if (isLoggingAnything) {
			if (isLoggingColorSensor) {
				StringBuilder sb = new StringBuilder();
				sb.append("PhotoReading: ").append(input.photoReading).append("\n");
				sb.append("PhotoState: ").append(input.photoState).append("\n");
				COLOR_SENSOR_LOGGER.fine(sb.toString());
				ANYTHING_LOGGER.fine("[ColorSensor]\n" + sb.toString());
			}
	
			if (isLoggingCamera) {
				StringBuilder sb = new StringBuilder();
				sb.append("Camera sees blocks: ").append(input.seesTarget).append("\n");
				sb.append("Heading to nearest: ").append(input.howCentered).append("\n");
				sb.append("Distance to nearest: ").append(input.boxDistance).append("\n");
				CAMERA_LOGGER.fine(sb.toString());
				ANYTHING_LOGGER.fine("[Camera]\n" + sb.toString());
			}
	
			if (isLoggingUltrasonicSensors) {
				StringBuilder sb = new StringBuilder();
				sb.append("FrontUltraDist: ").append(input.frontUltraDist).append("\n");
				sb.append("LeftFrontUltraDist: ").append(input.leftFrontUltraDist).append("\n");
				sb.append("LeftBackUltraDist: ").append(input.leftBackUltraDist).append("\n");
				sb.append("RightFrontUltraDist: ").append(input.rightFrontUltraDist).append("\n");
				sb.append("RightBackUltraDist: ").append(input.rightBackUltraDist).append("\n");
				sb.append("CloserSide: ").append(input.closerSide).append("\n");
				ULTRASONIC_SENSORS_LOGGER.fine(sb.toString());
				ANYTHING_LOGGER.fine("[Ultrasonic]\n" + sb.toString());
			}
	
			if (isLoggingGyroscope) {
				StringBuilder sb = new StringBuilder();
				sb.append("GyroAngle: ").append(input.gyroAngle).append("\n");
				GYROSCOPE_LOGGER.fine(sb.toString());
				ANYTHING_LOGGER.fine("[Gyroscope]\n" + sb.toString());
			}
		}
	}
	
	public void logOutputs(OutputStateVariables output) {
		if (isLoggingAnything) {
			if (isLoggingDriveTrain) {
				StringBuilder sb = new StringBuilder();
				sb.append("DriveTrainMethod: ").append(output.drivetrainMethod).append("\n");
				sb.append("DriveTrainPIDType: ").append(output.drivetrainPIDType).append("\n");
				sb.append("DriveTrainSpeed: ").append(output.drivetrainSpeed).append("\n");
				sb.append("pidSide: ").append(output.pidSide).append("\n");
				DRIVE_TRAIN_LOGGER.fine(sb.toString());
				ANYTHING_LOGGER.fine("[DriveTrain]\n" + sb.toString());
			}
		
			if (isLoggingHoppers) {
				StringBuilder sb = new StringBuilder();
				sb.append("HopperMethod: ").append(output.hopperMethod).append("\n");
				sb.append("LeftHatchOpen: ").append(output.leftHatchOpen).append("\n");
				sb.append("RightHatchOpen: ").append(output.rightHatchOpen).append("\n");
				HOPPERS_LOGGER.fine(sb.toString());
				ANYTHING_LOGGER.fine("[Hoppers]\n" + sb.toString());
			}
	
			if (isLoggingSorter) {
				StringBuilder sb = new StringBuilder();
				sb.append("SorterPosition: ").append(output.hopperPosition).append("\n");
				SORTER_LOGGER.fine(sb.toString());
				ANYTHING_LOGGER.fine("[Sorter]\n" + sb.toString());
			}

			if (isLoggingConveyor) {
				StringBuilder sb = new StringBuilder();
				sb.append("ConveyorMethod: ").append(output.conveyorMethod).append("\n");
				sb.append("ConveyorSpeed: ").append(output.conveyorSpeed).append("\n");
				CONVEYOR_LOGGER.fine(sb.toString());
				ANYTHING_LOGGER.fine("[Conveyor]\n" + sb.toString());
			}
			
			ANYTHING_LOGGER.fine("[GyroOut] zeroGyro: " + output.zeroGyro);
		}
	}

	public void logState(StateBase state) {
		if (isLoggingAnything && isLoggingRobotState) {
			StringBuilder sb = new StringBuilder();
			sb.append("Current State: ").append(state.getStateName()).append("\n");
			sb.append("Time in state: ").append(System.currentTimeMillis()-state.stateStartTime).append("\n");
			ROBOT_STATE_LOGGER.fine(sb.toString());
			ANYTHING_LOGGER.fine("[State]\n" + sb.toString());
		}
	}
}
