clear; clc;

jpath = "/home/matt/code/matlab_java_api";
useDev = true;
email = "admin@rit.edu";
password = "secret";

javaaddpath(jpath);

admin = FallRiskAdmin(email, password, useDev);

admin.getToken();

users = admin.getUserData();

n = length(users);

results = javaArray('UserResult', n);

fprintf("Found %d users that need to be evaluated.\n", n);
for i=1:n
    fprintf("User ID: %d; Email: %s\n", users(i).id, users(i).email);
    results(i) = UserResult(users(i).id, num2str(rand(1)));
end

msg = admin.postUserResults(results);