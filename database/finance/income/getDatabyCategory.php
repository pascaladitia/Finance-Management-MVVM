<?php

include_once('koneksi.php');

$category_id = $_GET['category_id'];

 $query = "SELECT A.* FROM income A, category_income B 
    WHERE A.category_id = B.category_id AND B.category_id = '$category_id'";

$get = mysqli_query($connect, $query);

$data = array();
if (mysqli_num_rows($get) > 0) {
    
    while ($row = mysqli_fetch_assoc($get)) {
        $data[] = $row;
    }
    set_response(true, "Data ditemukan", $data);
} else {
    set_response(false, "Data tcategory_idak ditemukan", $data);
}

function set_response($isSuccess, $message, $data) {

    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message,
        'data' => $data
    );
    echo json_encode($result);
}
