
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

    if (@$_GET['teacherId']) {
        @$teacherId = $_GET['teacherId'];

        $where = "where teacherId=" . $teacherId;
    } else {
        $teacherId = 0;
        $where = "";
    }

    $query = mysqli_query($conn, "select * from teacherrole " . $where);
    while ($row = mysqli_fetch_assoc($query)) {
        $data[] = array("teacherId" => $row['teacherId'], "assignedClass" => $row['assignedClass'], "assignedSub" => $row['assignedSub'], "periodNum" => $row['periodNum'], "dayVal" => $row['dayVal']);
    }
    return $data;
}

function response($data)
{
    echo  json_encode($data);
}

?>