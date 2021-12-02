clear; clc;

jpath = "/home/pilot/Programs/MSD/matlab_java_api";
useDev = true;
email = "admin@rit.edu";
password = "secret";

javaaddpath(jpath);

admin = FallRiskAdmin(email, password, useDev);

admin.getToken();

users = admin.getUserData();

n = length(users);

results = javaArray('UserResult', n);

% show found users
fprintf("Found %d users that need to be evaluated.\n", n);
for i=1:n
    fprintf("User ID: %d; Email: %s\n", users(i).id, users(i).email);
    results(i) = UserResult(users(i).id, num2str(rand(1)));
end

%% get survey information from the users
map = users(1).getSurveyData();
% possible fields (can be seen in the map)
% feet, inches

% gender: male=0, female=1, other=2

% age, weight, numFalls: integers

% strokeSide: left=0, right=1, both=2

% parkinsons, walkingAid, trapsFall, hearing, urine, medication
%   1 for true, 0 for false

% ex/
map.get('age')

%% get test data for users
numtests = 5; % number of tests

% these are java to matlab conversions to get enumerator values
% used for the getSensorData() method that requires an enum
ACCEL    = javaMethod('valueOf', 'UserData$Sensor', 'ACCEL');
GYRO     = javaMethod('valueOf', 'UserData$Sensor', 'GYRO');
GRAV     = javaMethod('valueOf', 'UserData$Sensor', 'GRAV');
LINACCEL = javaMethod('valueOf', 'UserData$Sensor', 'LINACCEL');
ROTDATA  = javaMethod('valueOf', 'UserData$Sensor', 'ROTDATA');
STEPCNT  = javaMethod('valueOf', 'UserData$Sensor', 'STEPCNT');

for i = 1:n
    fprintf("\nGetting tests for User ID: %d\n", users(i).id);
    % if the deserialization of the test data is successful, unpack the data
    if(users(i).deserializeTestData())
        for j = 1:numtests
            % for this current user and test, get the acceleration data

            % varname generates field names for each array of data in the following format:
            %   User<ID>_Test<ID>_<Sensor>Data
            varname = strcat('User', num2str(users(i).id), '_Test', num2str(j), '_AccelData');
            TestDataObjects.(varname) = users(i).getSensorData(j, ACCEL);

            % get the gyroscope data
            varname = strcat('User', num2str(users(i).id), '_Test', num2str(j), '_GyroData');
            TestDataObjects.(varname) = users(i).getSensorData(j, GYRO);

            % get the gravity data
            varname = strcat('User', num2str(users(i).id), '_Test', num2str(j), '_GravData');
            TestDataObjects.(varname) = users(i).getSensorData(j, GRAV);

            % get the linear acceleration data
            varname = strcat('User', num2str(users(i).id), '_Test', num2str(j), '_LinAccelData');
            TestDataObjects.(varname) = users(i).getSensorData(j, LINACCEL);

            % get the rotational velocity data
            varname = strcat('User', num2str(users(i).id), '_Test', num2str(j), '_RotData');
            TestDataObjects.(varname) = users(i).getSensorData(j, ROTDATA);

            % get the step count
            varname = strcat('User', num2str(users(i).id), '_Test', num2str(j), '_StepData');
            TestDataObjects.(varname) = users(i).getSensorData(j, STEPCNT);
        end
    else
        fprintf("No test data for User ID: %d\n", users(i).id);
    end
end


%% this is where the research data processing should occur on data in TestData struct

% Access data using getters in com/example/logintest/data/sensors/TestData
% getAccuracy() - integer representing sensor accuracy (read android docs)
% getTimestamp() - timestamp in seconds since unix epoch
% getValues() - column vector of floats, usually direction [X Y Z] (read android docs for each sensor)

% example:
% TestDataObjects.User7_Test4_AccelData(index).getTimestamp(); 

% msg = admin.postUserResults(results);
