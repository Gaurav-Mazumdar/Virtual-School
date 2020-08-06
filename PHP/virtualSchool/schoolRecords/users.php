
<?php

$conn = mysqli_connect("localhost", "id12928349_root", "pass@#1991", "id12928349_isl");

$request = $_SERVER['REQUEST_METHOD'];

$data = array();
switch ($request) {
    case 'GET':
        response(getData());
        break;


    case 'POST':
        response(addData());
        break;

    case 'PUT':
        response(updateData());
        break;

    case 'DELETE':
        response(removeData());
        break;

    default:
        # code...
        break;
}




function getData()
{
    global $conn;

    if (@$_GET['sid']) {
        @$sid = $_GET['sid'];

        $where = "where sid=" . $sid;
    } else {
        $sid = 0;
        $where = "";
    }

    $query = mysqli_query($conn, "select * from studentgrp " . $where);
    while ($row = mysqli_fetch_assoc($query)) {
        $data[] = array("studId" => $row['studId'], "stuName" => $row['stuName'], "stuRoll" => $row['stuRoll'], "code" => $row['code'], "sid_teacher" => $row['sid_teacher']);
    }
    return $data;
}

function addData()
{

    global $conn;

    $query = mysqli_query($conn, "insert into appuser(name,email,phone,address,password,type)values('" . $_POST['name'] . "','" . $_POST['email'] . "','" . $_POST['phone'] . "','" . $_POST['address'] . "','" . $_POST['password'] . "','" . $_POST['type'] . "')");

    if ($query == true) {
        $data[] = array("Message" => "User Created Successfully.");
    } else {
        $data[] = array("Message" => "Sorry!! User not created.");
    }

    return $data;
}

function updateData()
{
    global $conn;

    parse_str(file_get_contents('php://input'), $_PUT);

    if (@$_GET['id']) {
        @$id = $_GET['id'];

        $where = "where id=" . $id;
    } else {
        $id = 0;
        $where = "";
    }

    $query = mysqli_query($conn, "update user set name='" . $_PUT['name'] . "',  email='" . $_PUT['email'] . "',age='" . $_PUT['age'] . "' " . $where);

    if ($query == true) {
        $data[] = array("Message" => "update");
    } else {
        $data[] = array("Message" => "Not update !");
    }
    return $data;
}

function removeData()
{

    global $conn;

    if (@$_GET['id']) {
        @$id = $_GET['id'];

        $where = "where id=" . $id;
    } else {
        $id = 0;
        $where = "";
    }
    $query = mysqli_query($conn, "delete from user " . $where);

    if ($query == true) {
        $data[] = array("Message" => "delete");
    } else {
        $data[] = array("Message" => "Not delete !");
    }
    return $data;
}


function response($data)
{
    echo  json_encode($data);
}

?>