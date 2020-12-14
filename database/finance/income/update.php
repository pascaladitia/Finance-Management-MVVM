<?php

include_once('koneksi.php');

if (!empty($_POST['income_id']) && !empty($_POST['income_date']) && !empty($_POST['income_money'])
    && !empty($_POST['category_id'])) {

    $income_id = $_POST['income_id'];
    $income_date = $_POST['income_date'];
    $income_money = $_POST['income_money'];
    $category_id = $_POST['category_id'];

    $query = "UPDATE income set income_date = '$income_date', income_money = '$income_money',
             category_id = '$category_id' WHERE income_id = '$income_id'";

    $update = mysqli_query($connect, $query);

    if($update) {
        set_response(true, "Success update data");
    } else {
        set_response(false, "False update data");
    }
} else {
    set_response(false, "income_id, income_date & income_money harus diisi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );
    
    echo json_encode($result);
}