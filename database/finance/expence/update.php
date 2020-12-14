<?php

include_once('koneksi.php');

if (!empty($_POST['expence_id']) && !empty($_POST['expence_date']) && !empty($_POST['expence_money'])
    && !empty($_POST['category_id'])) {

    $expence_id = $_POST['expence_id'];
    $expence_date = $_POST['expence_date'];
    $expence_money = $_POST['expence_money'];
    $category_id = $_POST['category_id'];

    $query = "UPDATE expence set expence_date = '$expence_date', expence_money = '$expence_money', 
    category_id = '$category_id' WHERE expence_id = '$expence_id'";

    $update = mysqli_query($connect, $query);

    if($update) {
        set_response(true, "Success update data");
    } else {
        set_response(false, "False update data");
    }
} else {
    set_response(false, "expence_id, expence_date & expence_money harus diisi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );
    
    echo json_encode($result);
}