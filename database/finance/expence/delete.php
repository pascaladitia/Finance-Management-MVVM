<?php

include_once('koneksi.php');

if (!empty($_POST['expence_id'])) {

    $expence_id = $_POST['expence_id'];
    
    $query = "DELETE FROM expence WHERE expence_id = '$expence_id'";

    $delete = mysqli_query($connect, $query);

    if($delete) {
        set_response(true, "Success delete data");
    } else {
        set_response(false, "Failed delete data");
    }
} else {
    set_response(false, "expence_id harus diisi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );

    echo json_encode($result);
}