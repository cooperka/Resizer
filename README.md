# Batch Image Resizer

Simple utility to recursively resize all image files (`.png`, `.jpg`) within the parent directory to fit within a specified max dimension. For example, an image of size `200x600px` resized to a max dimension of `300px` will be end up being `100x300px`. Images smaller than the max dimension will be untouched.

Essentially this is a wrapper for thebuzzmedia's [imgscalr][1] library, performing the scaling in bulk.

*Maintained from around 2010 to 2011 for my father's use; see [History](/README.md#history) below.*

## Usage

#### Easy use

Windows only.

1. Move the **folder** containing `resize.bat`, `resize.jar`, and `imgscalr.jar` into the **folder** containing all the images to be resized
2. Edit `resize.bat` to specify the max dimension for all images
3. Double-click to run `resize.bat`
  * Warning: this will recursively resize all photos in the parent directory! There's no going back...

#### Manual use

1. Compile [imgscalr][1] from source
1. Compile `ListFiles.java` from source
3. Follow "easy use" with the newly generated files

## History

My father is the girls' cross country coach at Grosse Pointe North high school, and he puts together slideshows at the end of every season for the entire sports department. This involves collecting thousands of photos from parents and photographers across hundreds of sports events. He then sorts through those photos and chooses the best ones to put into a slideshow. In order to make this task easier and to make the final presentation an acceptable file size, most photos must be downsized significantly.

I wrote this utility so he could downsize the photos efficiently, instead of using Photoshop's batch utility which is much slower and harder to use.

*Note:
All the code here is in its (mostly) original form, and hasn't been modified since I wrote it in high school.*

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## Credits

1. thebuzzmedia's [imgscalr][1] library

[1]: https://github.com/thebuzzmedia/imgscalr
