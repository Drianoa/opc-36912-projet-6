import {Component, input, ChangeDetectionStrategy} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  MatCard,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from '@angular/material/card';
import {PostResponse} from "../../core/interfaces/postResponse.interface";

/**
 * Composant de prévisualisation d'article utilisant les signaux Angular 20+
 * Affiche un résumé d'article avec troncature du contenu à 5 lignes maximum
 */
@Component({
  selector: 'app-article-preview',
  imports: [CommonModule, MatCard, MatCardHeader, MatCardTitle, MatCardSubtitle, MatCardContent],
  templateUrl: './post-preview.component.html',
  styleUrl: './post-preview.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PostPreviewComponent {
  /** Signal d'entrée pour les données du post */
  post = input.required<PostResponse>();
}
