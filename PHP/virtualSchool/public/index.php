<?php

use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;

require '../vendor/autoload.php';

require '../includes/DbOperations.php';

$app = new \Slim\App([
        'settings' => [
                'displayErrorDetails' => true
        ]
]);


$app->post('/createOrganisation', function (Request $request, Response $response) {
        if (!haveEmptyParameters(array('orgName', 'estDate', 'address', 'contact', 'email', 'password', 'orgHead', 'run'), $request, $response)) {
                $request_data = $request->getParsedBody();

                $orgName = $request_data['orgName'];
                $estDate = $request_data['estDate'];
                $address = $request_data['address'];
                $contact = $request_data['contact'];
                $email = $request_data['email'];
                $password = $request_data['password'];
                $orgHead = $request_data['orgHead'];
                $run = $request_data['run'];

                $db = new DbOperations;

                $result = $db->createOrganisation($orgName, $estDate, $address, $contact, $email, $password, $orgHead, $run);

                if ($result == USER_REGISTERED) {

                        $message = array();
                        $message['error'] = false;
                        $message['message'] = 'Organisation registered successfully';

                        $response->write(json_encode($message));
                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(201);
                } else if ($result == USER_UNREGISTERED) {

                        $message = array();
                        $message['error'] = true;
                        $message['message'] = 'Some error occurred';

                        $response->write(json_encode($message));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(422);
                } else if ($result == USER_EXIST) {
                        $message = array();
                        $message['error'] = true;
                        $message['message'] = 'Organisation already registered';

                        $response->write(json_encode($message));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(422);
                }
        }
        return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(422);
});


$app->post('/orgLogin', function (Request $request, Response $response) {

        if (!haveEmptyParameters(array('email', 'password'), $request, $response)) {
                $request_data = $request->getParsedBody();

                $email = $request_data['email'];
                $password = $request_data['password'];

                $db = new DbOperations;

                $result = $db->orgLogin($email, $password);

                if ($result == USER_AUTHENTICATED) {

                        $org = $db->getOrgByEmail($email);
                        $response_data = array();

                        $response_data['error'] = false;
                        $response_data['message'] = 'Login Successful';
                        $response_data['org'] = $org;

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                } else if ($result == USER_NOT_FOUND) {
                        $response_data = array();

                        $response_data['error'] = true;
                        $response_data['message'] = 'User not exist';

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                } else if ($result == USER_PASSWORD_DO_NOT_MATCH) {
                        $response_data = array();

                        $response_data['error'] = true;
                        $response_data['message'] = 'Invalid credential';

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                } else if ($result == USER_DEACTIVATED) {
                        $response_data = array();

                        $response_data['error'] = true;
                        $response_data['message'] = 'Account currently deactive';

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                }
        }

        return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(422);
});


$app->put('/orgLiveState/{orgCode}', function (Request $request, Response $response, array $args) {
        $orgCode = $args['orgCode'];
        if (!haveEmptyParameters(array('live'), $request, $response)) {
                $request_data = $request->getParsedBody();

                $live = $request_data['live'];
                $email = $request_data['email'];
                $db = new DbOperations;

                if ($db->updateStatus($live, $email, $orgCode)) {
                        $response_data = array();

                        $response_data['error'] = false;
                        $response_data['message'] = 'Status Updated Successfully';
                        $org = $db->getOrgByEmail($email);
                        $response_data['org'] = $org;

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                } else {
                        $response_data = array();

                        $response_data['error'] = true;
                        $response_data['message'] = 'Try again later';

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(422);
                }
        }
        return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(500);
});

$app->post('/createTeacher', function (Request $request, Response $response) {
        if (!haveEmptyParameters(array('orgCode', 'name', 'email', 'password', 'contact', 'qualification', 'address'), $request, $response)) {
                $request_data = $request->getParsedBody();

                $orgCode = $request_data['orgCode'];
                $name = $request_data['name'];
                $email = $request_data['email'];
                $password = $request_data['password'];
                $contact = $request_data['contact'];
                $qualification = $request_data['qualification'];
                $address = $request_data['address'];

                $db = new DbOperations;

                $result = $db->createNewTeacher($orgCode, $name, $email, $password, $contact, $qualification, $address);

                if ($result == USER_REGISTERED) {

                        $message = array();
                        $message['error'] = false;
                        $message['message'] = 'Teacher Added successfully';

                        $response->write(json_encode($message));
                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(201);
                } else if ($result == USER_UNREGISTERED) {

                        $message = array();
                        $message['error'] = true;
                        $message['message'] = 'Some error occurred';

                        $response->write(json_encode($message));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(422);
                } else if ($result == USER_EXIST) {
                        $message = array();
                        $message['error'] = true;
                        $message['message'] = 'Teacher already exists';

                        $response->write(json_encode($message));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(422);
                }
        }
        return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(500);
});

$app->post('/teacherLogin', function (Request $request, Response $response) {

        if (!haveEmptyParameters(array('email', 'password'), $request, $response)) {
                $request_data = $request->getParsedBody();

                $email = $request_data['email'];
                $password = $request_data['password'];

                $db = new DbOperations;

                $result = $db->teacherLogin($email, $password);

                if ($result == USER_AUTHENTICATED) {

                        $faculty = $db->getTeacherByEmail($email);
                        $response_data = array();

                        $response_data['error'] = false;
                        $response_data['message'] = 'Login Successful';
                        $response_data['fac'] = $faculty;

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                } else if ($result == USER_NOT_FOUND) {
                        $response_data = array();

                        $response_data['error'] = true;
                        $response_data['message'] = 'User not exist';

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                } else if ($result == USER_PASSWORD_DO_NOT_MATCH) {
                        $response_data = array();

                        $response_data['error'] = true;
                        $response_data['message'] = 'Invalid credential';

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                } else if ($result == USER_DEACTIVATED) {
                        $response_data = array();

                        $response_data['error'] = true;
                        $response_data['message'] = 'Account currently deactive';

                        $response->write(json_encode($response_data));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(200);
                }
        }

        return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(422);
});

$app->post('/createTeacherRole', function (Request $request, Response $response) {
        if (!haveEmptyParameters(array('orgCode', 'teacherId', 'teacherName', 'assignedClass', 'assignedSub', 'periodNum', 'dayVal'), $request, $response)) {
                $request_data = $request->getParsedBody();

                $orgCode = $request_data['orgCode'];
                $teacherId = $request_data['teacherId'];
                $teacherName = $request_data['teacherName'];
                $assignedClass = $request_data['assignedClass'];
                $assignedSub = $request_data['assignedSub'];
                $periodNum = $request_data['periodNum'];
                $dayVal = $request_data['dayVal'];

                $db = new DbOperations;

                $result = $db->assignRole($orgCode, $teacherId, $teacherName, $assignedClass, $assignedSub, $periodNum, $dayVal);

                if ($result == USER_REGISTERED) {

                        $message = array();
                        $message['error'] = false;
                        $message['message'] = 'Role assigned successfully';

                        $response->write(json_encode($message));
                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(201);
                } else if ($result == USER_UNREGISTERED) {

                        $message = array();
                        $message['error'] = true;
                        $message['message'] = 'Some error occurred';

                        $response->write(json_encode($message));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(422);
                } /*else if ($result == USER_EXIST) {
                        $message = array();
                        $message['error'] = true;
                        $message['message'] = 'Role already assigned for this teacher';

                        $response->write(json_encode($message));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(422);
                }*/
        }
        return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(422);
});

$app->post('/createAssignment', function (Request $request, Response $response) {
        if (!haveEmptyParameters(array('tId', 'std', 'period', 'subject', 'topic', 'link', 'assignCode'), $request, $response)) {
                $request_data = $request->getParsedBody();

                $tId = $request_data['tId'];
                $std = $request_data['std'];
                $period = $request_data['period'];
                $subject = $request_data['subject'];
                $topic = $request_data['topic'];
                $link = $request_data['link'];
                $assignCode = $request_data['assignCode'];

                $db = new DbOperations;

                $result = $db->createAssignment($tId, $std, $period, $subject, $topic, $link, $assignCode);

                if ($result == USER_REGISTERED) {

                        $message = array();
                        $message['error'] = false;
                        $message['message'] = 'Topic created successfully';

                        $response->write(json_encode($message));
                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(201);
                } else if ($result == USER_UNREGISTERED) {

                        $message = array();
                        $message['error'] = true;
                        $message['message'] = 'Some error occurred';

                        $response->write(json_encode($message));

                        return $response
                                ->withHeader('Content-type', 'application/json')
                                ->withStatus(422);
                }
        }
        return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(500);
});


function haveEmptyParameters($required_params, $request, $response)
{
        $error = false;
        $error_params = '';
        $request_params = $request->getParsedBody();

        foreach ($required_params as $param) {
                if (!isset($request_params[$param]) || strlen($request_params[$param]) <= 0) {
                        $error = true;
                        $error_params .= $param . ', ';
                }
        }

        if ($error) {
                $error_detail = array();
                $error_detail['error'] = true;
                $error_detail['message'] = 'Required Parameters ' . substr($error_params, 0, -2) . 'are missing or empty';
                $response->write(json_encode($error_detail));
        }
        return $error;
}

$app->run();
