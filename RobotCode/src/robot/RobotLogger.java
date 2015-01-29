package robot;
import subsystems.*;
import states.*;
import java.util.logging.*;

import java.io.IOException;

import static robot.Enums.*;

public class RobotLogger {
	private static final Logger ANYTHING_LOGGER = Logger.getLogger("Anything_Logger");
	private static final Logger COLOR_SENSOR_LOGGER = Logger.getLogger("Color_Sensor_Log");
	private static final Logger CAMERA_LOGGER = Logger.getLogger("Camera_Log");
	private static final Logger IR_SENSORS_LOGGER = Logger.getLogger("IR_Sensors_Log");
	private static final Logger GYROSCOPE_LOGGER = Logger.getLogger("Gyroscope_Log");
	private static final Logger ROBOT_STATE_LOGGER = Logger.getLogger("Robot_State_Log");
	private static final Logger DRIVE_TRAIN_LOGGER = Logger.getLogger("Drive_Train_Logger");
	private static final Logger HOPPERS_LOGGER = Logger.getLogger("Hoppers_Log");
	private static final Logger SORTER_LOGGER = Logger.getLogger("Sorter_Log");
	private static final Logger CONVEYOR_LOGGER = Logger.getLogger("Conveyor_Log");
	private static final Logger[] ALL_LOGGERS = {ANYTHING_LOGGER, COLOR_SENSOR_LOGGER, CAMERA_LOGGER,
													IR_SENSORS_LOGGER, GYROSCOPE_LOGGER, ROBOT_STATE_LOGGER,
													DRIVE_TRAIN_LOGGER, HOPPERS_LOGGER, SORTER_LOGGER, CONVEYOR_LOGGER};

	private static final boolean isLoggingAnything = true;
	private static final boolean isLoggingColorSensor = true;
	private static final boolean isLoggingCamera = true;
	private static final boolean isLoggingIRSensors = true;
	private static final boolean isLoggingGyroscope = true;
	private static final boolean isLoggingRobotState = true;
	private static final boolean isLoggingDriveTrain = true;
	private static final boolean isLoggingHoppers = true;
	private static final boolean isLoggingSorter = true;
	private static final boolean isLoggingConveyor = true;

	private static final String BASE_DIR = "logs";
	private static final String ANYTHING_FILE = "Anything.log";
	private static final String COLOR_SENSOR_FILE = "ColorSensor.log";
	private static final String CAMERA_FILE = "Camera.log";
	private static final String IR_SENSORS_FILE = "IR.log";
	private static final String GYROSCOPE_FILE = "Gyroscope.log";
	private static final String ROBOT_STATE_FILE = "RobotState.log";
	private static final String DRIVE_TRAIN_FILE = "DriveTrain.log";
	private static final String HOPPERS_FILE = "Hoppers.log";
	private static final String SORTER_FILE = "Sorter.log";
	private static final String CONVEYOR_FILE = "Conveyor.log";
	private static final String[] ALL_FILES = {ANYTHING_FILE, COLOR_SENSOR_FILE, CAMERA_FILE, 
												IR_SENSORS_FILE, GYROSCOPE_FILE, ROBOT_STATE_FILE,
												DRIVE_TRAIN_FILE, HOPPERS_FILE, SORTER_FILE, CONVEYOR_FILE};

	public RobotLogger() {
		//Sets up file handler(s)
		try {
			for (int i = 0; i < ALL_LOGGERS.length; i++) {
				FileHandler fileHandler = new FileHandler(BASE_DIR + "/" + ALL_FILES[i]);
				SimpleFormatter sf = new SimpleFormatter();
				fileHandler.setFormatter(sf);
				ALL_LOGGERS[i].addHandler(fileHandler);
				ALL_LOGGERS[i].setUseParentHandlers(false);
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
				COLOR_SENSOR_LOGGER.info(sb.toString());
				ANYTHING_LOGGER.info("[ColorSensor]\n" + sb.toString());
			}
	
			if (isLoggingCamera) {
				StringBuilder sb = new StringBuilder();
				sb.append("Camera sees blocks: ").append(input.seesTarget).append("\n");
				sb.append("Heading to nearest: ").append(input.howCentered).append("\n");
				sb.append("Distance to nearest: ").append(input.boxDistance).append("\n");
				CAMERA_LOGGER.info(sb.toString());
				ANYTHING_LOGGER.info("[Camera]\n" + sb.toString());
			}
	
			if (isLoggingIRSensors) {
				StringBuilder sb = new StringBuilder();
				sb.append("FrontIRDist: ").append(input.frontIRDist).append("\n");
				sb.append("RightFrontIRDist: ").append(input.rightFrontIRDist).append("\n");
				sb.append("RightBackIRDist: ").append(input.rightBackIRDist).append("\n");
				IR_SENSORS_LOGGER.info(sb.toString());
				ANYTHING_LOGGER.info("[IR]\n" + sb.toString());
			}
	
			if (isLoggingGyroscope) {
				StringBuilder sb = new StringBuilder();
				sb.append("GyroAngle: ").append(input.gyroAngle).append("\n");
				GYROSCOPE_LOGGER.info(sb.toString());
				ANYTHING_LOGGER.info("[Gyroscope]\n" + sb.toString());
			}
		}
	}
	
	public void logOutputs(OutputStateVariables output) {
		if (isLoggingAnything) {
			if (isLoggingDriveTrain) {
				StringBuilder sb = new StringBuilder();
				if (output.driveTrainMethod != null)
					sb.append("DriveTrainMethod: ").append(output.driveTrainMethod.name()).append("\n");
				sb.append("DriveTrainSpeed: ").append(output.driveTrainSpeed).append("\n");
				DRIVE_TRAIN_LOGGER.info(sb.toString());
				ANYTHING_LOGGER.info("[DriveTrain]\n" + sb.toString());
			}
		
			if (isLoggingHoppers) {
				StringBuilder sb = new StringBuilder();
				if (output.hopperMethod != null)
					sb.append("HopperMethod: ").append(output.hopperMethod.name()).append("\n");
				sb.append("HopperLeftOpen: ").append(output.hopperLeftOpen).append("\n");
				sb.append("HopperRightOpen: ").append(output.hopperRightOpen).append("\n");
				HOPPERS_LOGGER.info(sb.toString());
				ANYTHING_LOGGER.info("[Hoppers]\n" + sb.toString());
			}
	
			if (isLoggingSorter) {
				StringBuilder sb = new StringBuilder();
				if (output.sorterMethod != null)
					sb.append("SorterMethod: ").append(output.sorterMethod.name()).append("\n");
				if (output.sorterPosition != null)
					sb.append("SorterPosition: ").append(output.sorterPosition.name()).append("\n");
				SORTER_LOGGER.info(sb.toString());
				ANYTHING_LOGGER.info("[Sorter]\n" + sb.toString());
			}

			if (isLoggingConveyor) {
				StringBuilder sb = new StringBuilder();
				if (output.conveyorMethod != null)
					sb.append("ConveyorMethod: ").append(output.conveyorMethod.name()).append("\n");
				sb.append("ConveyorSpeed: ").append(output.conveyorSpeed).append("\n");
				CONVEYOR_LOGGER.info(sb.toString());
				ANYTHING_LOGGER.info("[Conveyor]\n" + sb.toString());
			}
			
			ANYTHING_LOGGER.info("[GyroOut] zeroGyro: " + output.zeroGyro);
		}
	}

	public void logState(StateBase state) {
		if (state != null && isLoggingAnything && isLoggingRobotState) {
			StringBuilder sb = new StringBuilder();
			sb.append("Current State: ").append(state.getStateName()).append("\n");
			sb.append("Time in state: ").append(state.getElapsedTime()).append("\n");
			ROBOT_STATE_LOGGER.info(sb.toString());
			ANYTHING_LOGGER.info("[State]\n" + sb.toString());
		}
	}
}
