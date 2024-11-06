package com.airbnb.airbnb;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirbnbApplication {

    public static void main(String[] args) {

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "duvbxqzxx",
                "api_key", "896479486825397",
                "api_secret", "9MonU-R2lWu3bONuTSRDqy_9je4"));
        SingletonManager manager = new SingletonManager();
        manager.setCloudinary(cloudinary);
        manager.init();
        SpringApplication.run(AirbnbApplication.class, args);
    }

}
