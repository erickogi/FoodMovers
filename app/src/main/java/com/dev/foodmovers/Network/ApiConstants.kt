package com.dev.lishabora.Utils.Network

class ApiConstants {


    companion object {


        //KEY VALUES
        var resultCode: String = "ResultCode"
        var resultDescription: String = "ResultDescription"

        var baseUrl: String = "http://lishabora.net/512/Apis/"

        //Accounts
        var Accounts: String = baseUrl + "Accounts/"
        var PhoneAuth: String = Accounts + "AuthPhone.php"
        var LoginAuth: String = Accounts + "Login.php"
        var SignAuth: String = Accounts + "SignUp.php"
        var PasswordAuth: String = Accounts + "AuthPassword.php"
        var newPassordConfirm: String = Accounts + "ChangePassword.php"
        var otpRequest: String = Accounts + "ForgotPassword.php"

        //Admin
        var Admin: String = baseUrl + "Users/"
        // var Traders: String = Trader + "List.php"
        var CreateAdmin: String = Admin + "Create.php"
        var UpdateAdmin: String = Admin + "Update.php"


        //Traders
        var Trader: String = baseUrl + "Traders/"
        var Traders: String = Trader + "List.php"
        var CreateTrader: String = Trader + "Create.php"
        var UpdateTrader: String = Trader + "Update.php"

        //Products
        var Product: String = baseUrl + "Products/"
        var Products: String = Product + "List.php"
        var CreateProducts: String = Product + "Create.php"
        var UpdateProducts: String = Product + "Update.php"


        //FOOD
        var Food: String = baseUrl + "Food/"
        var foods: String = Food + "List.php"
        var CreateFarmer: String = Food + "Create.php"
        var UpdateFarmer: String = Food + "Update.php"

        //CART
        var Cart: String = baseUrl + "Cart/"
        var carts: String = Cart + "List.php"
        var CreateCart: String = Cart + "Create.php"
        var updateCart: String = Cart + "Update.php"


        //ORDER
        var Order: String = baseUrl + "Orders/"
        var orders: String = Order + "List.php"
        var CreateOrder: String = Order + "Create.php"
        var UpdateOrder: String = Order + "Update.php"



        //Trader Products
        var TraderProduct: String = baseUrl + "Traders/"
        var TraderProducts: String = TraderProduct + "Products.php"
        var Subscribed: String = TraderProduct + "ProductSubscribe.php"

        //var TraderCreateProducts: String = TraderProduct + "Create.php"
        //var TraderUpdateProducts: String = TraderProduct + "Update.php"


        //Trader FragmentRoutes
        var TraderRoute: String = baseUrl + "Traders/"
        var TraderRoutes: String = TraderRoute + "Routes.php"
        var TraderCreateRoutes: String = TraderRoute + "Create.php"
        var TraderUpdateRoutes: String = TraderRoute + "Update.php"


        //Trader Farmers
        var TraderFarmer: String = baseUrl + "Traders/"
        var TraderFarmers: String = TraderFarmer + "Farmers.php"
        var TraderCreateFarmers: String = TraderFarmer + "Create.php"
        var TraderUpdateFarmers: String = TraderFarmer + "Update.php"


        //Routes
        var Route: String = baseUrl + "Routes/"
        var CreateRoutes: String = Route + "Create.php"
        var UpdateRoutes: String = Route + "Update.php"


        //Cycles
        var Cycle: String = baseUrl + "Cycles/"
        var Cycles: String = Cycle + "List.php"


        //Units
        var Unit: String = baseUrl + "Units/"
        var Units: String = Unit + "List.php"


    }
}
