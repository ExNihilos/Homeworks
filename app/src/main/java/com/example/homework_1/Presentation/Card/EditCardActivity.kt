package com.example.homework_1.Presentation.Card

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.homework_1.BuildConfig
import com.example.homework_1.Domain.Models.Card
import com.example.homework_1.Domain.Models.Category
import com.example.homework_1.Domain.Models.Image
import com.example.homework_1.Presentation.Card.EditCardActivity.Companion.REQUEST_CAMERA_PERMISSION
import com.example.homework_1.Presentation.Card.EditCardActivity.Companion.REQUEST_CODE_IMAGE_CAPTURE
import com.example.homework_1.Presentation.Card.EditCardActivity.Companion.REQUEST_CODE_PICK_IMAGE_GALLARY
import com.example.homework_1.Presentation.Card.EditCardActivity.Companion.SIDE_BACK
import com.example.homework_1.Presentation.Card.EditCardActivity.Companion.SIDE_FRONT
import com.example.homework_1.Presentation.Card.EditCardActivity.Companion.categoryextra
import com.example.homework_1.Presentation.Category.CategoryListActivity
import com.example.homework_1.Providers.CardProvider
import com.example.homework_1.R
import kotlinx.android.synthetic.main.activity_edit_card_activty.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException


class EditCardActivity : AppCompatActivity() {

    var cardProvider = CardProvider()
    var id = cardProvider.getId()?.plus(1)
    var photoId = 1
    var currentSide = 1
    var imageFront: Image? = null
    var imageBack: Image? = null
    private var currentImageFile: File? = null

    companion object
    {
        const val REQUEST_CODE = 1
        const val REQUEST_CODE_PICK_IMAGE_GALLARY = 2
        const val REQUEST_CODE_IMAGE_CAPTURE = 3
        const val REQUEST_CAMERA_PERMISSION = 4
        const val SIDE_FRONT = 1
        const val SIDE_BACK = 2
        var categoryextra = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_card_activty)

        currentSide = SIDE_FRONT

    }

    fun chooseClick(view: View)
    {
        launchImagePicker()
    }

    fun takephotoClick(view: View)
    {
        if (!checkCameraGrantedPermission() && doRequestPermission(REQUEST_CAMERA_PERMISSION)) return
        takePhoto()
    }

    private fun launchImagePicker() {
        // Интент для получения всех приложений которые могут отображать изображения
        val getIntent = Intent(Intent.ACTION_GET_CONTENT)
        getIntent.type = "image/*"

        // Вызываем системное диалоговое окно для выбора приложения, которое умеет отображать изображения
        // и возвращать выбранную фотографию
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = "image/*"

        val chooserIntent = Intent.createChooser(getIntent, "Выберите изображение")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))
        // Запускаем приложения и ожидаем результат
        startActivityForResult(chooserIntent, REQUEST_CODE_PICK_IMAGE_GALLARY)
    }

    fun backClick(view: View)
    {
        onBackPressed()
    }

    fun saveClick(view: View)
    {
        if (id==null)
        {
            id=1
        }

        val name = NameIT.text.toString()
        val category = Category(id!!.toInt(), CategoryIT.text.toString())
        val percent = PercentIT.text.toString()

        if (imageFront == null || imageBack ==null)
        {
            Toast.makeText(this,"Сфотографируйте лицевую сторону карты", LENGTH_SHORT).show()
            return
        }

         val images = mutableListOf(
            imageFront!!, imageBack!!
           // Image(1, "android.resource://com.example.homework_1/drawable/gaini_47"),
            //Image(2, "android.resource://com.example.homework_1/drawable/gaini_47")
         )


        if (name == "" || category.title == "" || percent == "")
        {
            Toast.makeText(this, "Заполнены не все поля", LENGTH_SHORT).show()
        }

        else
        {
            if (id==null)
            {
                id=1
            }

            val card1 = Card(
                id!!, name, category, try {
                    percent.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Неверный формат скидки", LENGTH_SHORT).show(); return
                }, images
            )

            if (percent.toInt() > 100)
            {
                Toast.makeText(this, "Скидка не может быть больше 100%!", LENGTH_SHORT).show()
                return
            }

            cardProvider.saveCardToDB(card1)
            id = id!!+1
            val intent3 = Intent(this, CardListActivity::class.java)
            intent3.putExtra(Card::class.java.simpleName, card1)
            setResult(Activity.RESULT_OK, intent3)
            finish()
        }
    }

    fun categoryClick(view: View)
    {
        val intent1 = Intent(this, CategoryListActivity::class.java)
        startActivityForResult(intent1, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK)
        {
            return
        }

        when (requestCode)
        {
            REQUEST_CODE -> CategoryIT.setText(data?.getStringExtra(categoryextra))
            REQUEST_CODE_PICK_IMAGE_GALLARY -> {
                try {
                    //Получаем URI изображения, преобразуем его в Bitmap
                    //объект и отображаем в элементе ImageView нашего интерфейса:
                    val imageUri = data?.data ?: return
                   // val imageStream = getContentResolver().openInputStream(imageUri)
                    //var selectedImage = BitmapFactory.decodeStream(imageStream)


                   // when (currentSide) {
                       // SIDE_FRONT -> {
                            imageFront =  Image(photoId,imageUri.toString())//Image(1, "android.resource://com.example.homework_1/drawable/gaini_47")//Image(1, currentImageFile?.path)
                       // }
                       // SIDE_BACK -> {
                            imageBack =  Image(photoId+1, imageUri.toString())//"android.resource://com.example.homework_1/drawable/gaini_47")//Image(2, currentImageFile?.path)
                       // }
                            photoId++
                 //   }
//                    ivPhoto.setImageBitmap(selectedImage)
                }

                catch (e: FileNotFoundException)
                {
                    // Эта ошибка отобразится в случае если не удалось найти изображение
                    e.printStackTrace()
                }
            }

            REQUEST_CODE_IMAGE_CAPTURE -> {
               // when (currentSide) {
                   // SIDE_FRONT -> {
                        imageFront = Image(1, currentImageFile?.path.toString())
                 //   }
                   // SIDE_BACK -> {
                        imageBack = Image(2, currentImageFile?.path.toString())
                  //  }

               // }
            }

        }

    }

   private fun takePhoto()
    {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) == null)
        {
            Toast.makeText(this, "На вашем устройстве недоступна камера", LENGTH_SHORT).show()
            return
        }

        // Создаём файл для изображения
        currentImageFile = createImageFile()

        val file = currentImageFile ?: return

        // Если файл создался — получаем его URI
        val imageUri = FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID + ".fileprovider",file) //FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", file)
        // Передаём URI в камеру
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE)
    }

    private fun createImageFile(): File? {
        // Генерируем имя файла
        val filename = System.currentTimeMillis().toString() + ".jpg"

        // Получаем приватную директорию на карте памяти для хранения изображений
        // Выглядит она примерно так: /sdcard/Android/data/info.goodline.department.learnandroid./files/Pictures
        // Директория будет создана автоматически, если ещё не существует
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        // Создаём файл
        val image = File(storageDir, filename)

        return try {
            if (image.createNewFile()) {
                image
            } else null
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }

    /**
     * Проверить права доступа к камере
     *
     * @return true - права доступа выданы
     */
    private fun checkCameraGrantedPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Проверить нужно ли запрашивать права доступа на текущем устройстве
     * @param requestCode - код для запроса прав доступа
     * @return true если необходимо запросить парва доступа
     */
    private fun doRequestPermission(requestCode: Int): Boolean {
        // Запрашиваем права доступа только на Android 6 и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                requestCode
            )
            return true
        }
        return false
    }


    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Здесь обрабатывается результат выдачи прав доступа, которые мы запросили
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto()
            }
        }
    }
}


