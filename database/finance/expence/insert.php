<?php

include_once('koneksi.php');

if (!empty($_POST['expence_date']) && !empty($_POST['expence_money']) 
    && !empty($_POST['category_id'])) {

    $expence_date = $_POST['expence_date'];
    $expence_money = $_POST['expence_money'];
    $category_id = $_POST['category_id'];

    $query = "INSERT INTO expence(expence_date, expence_money, category_id) 
        VALUES ('$expence_date','$expence_money','$category_id')";

    $insert = mysqli_query($connect, $query);

    if($insert) {
        set_response(true, "Success insert data");
    } else {
        set_response(false, "Failed insert data");
    } 
} else {
        set_response(false, "expence_date & expence_money harus diisi");
    }

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );

    echo json_encode($result);
}
