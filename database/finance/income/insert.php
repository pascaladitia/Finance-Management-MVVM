<?php

include_once('koneksi.php');

if (!empty($_POST['income_date']) && !empty($_POST['income_money'])
    && !empty($_POST['category_id'])) {

    $income_date = $_POST['income_date'];
    $income_money = $_POST['income_money'];
    $category_id = $_POST['category_id'];

    $query = "INSERT INTO income(income_date, income_money, category_id) 
        VALUES ('$income_date','$income_money','$category_id')";

    $insert = mysqli_query($connect, $query);

    if($insert) {
        set_response(true, "Success insert data");
    } else {
        set_response(false, "Failed insert data");
    } 
} else {
        set_response(false, "income_date & income_money harus diisi");
    }

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );

    echo json_encode($result);
}
