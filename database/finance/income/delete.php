<?php

include_once('koneksi.php');

if (!empty($_POST['income_id'])) {

    $income_id = $_POST['income_id'];
    
    $query = "DELETE FROM income WHERE income_id = '$income_id'";

    $delete = mysqli_query($connect, $query);

    if($delete) {
        set_response(true, "Success delete data");
    } else {
        set_response(false, "Failed delete data");
    }
} else {
    set_response(false, "income_id harus diisi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );

    echo json_encode($result);
}