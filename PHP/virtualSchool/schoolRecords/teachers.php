
<?php

//$conn = mysqli_connect("localhost", "id12928349_root", "pass@#1991", "id12928349_isl");
$conn = mysqli_connect("localhost", "root", "", "test");

$request = $_SERVER['REQUEST_METHOD'];

$data = array();
switch ($request) {
    case 'GET':
        response(getData());
        break;


        /*case 'POST':
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
        break;*/
}

function getData()
{
    global $conn;

    if (@$_GET['orgCode']) {
        @$orgCode = $_GET['orgCode'];

        $where = "where orgCode=" . $orgCode;
    } else {
        $orgCode = 0;
        $where = "";
    }

    $query = mysqli_query($conn, "select * from teachers " . $where);
    while ($row = mysqli_fetch_assoc($query)) {
        $data[] = array("teacherId" => $row['teacherId'], "orgCode" => $row['orgCode'], "name" => $row['name'], "status" => $row['status'], "live" => $row['live']);
    }
    return $data;
}

function response($data)
{
    echo  json_encode($data);
}

?>