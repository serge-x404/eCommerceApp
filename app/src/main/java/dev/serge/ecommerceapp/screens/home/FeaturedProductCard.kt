package dev.serge.ecommerceapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.rememberAsyncImagePainter
import dev.serge.ecommerceapp.model.Product

@Composable
fun FeaturedProductCard(
    product: Product,
    onProductClick: () -> Unit
) {
    Card(
        onClick = onProductClick,
        modifier = Modifier
            .width(280.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box{
            DiscountBadge(
                5, Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .zIndex(2f)
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.imageURL),
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                product.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "$${product.price}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating"
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    product.id,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}